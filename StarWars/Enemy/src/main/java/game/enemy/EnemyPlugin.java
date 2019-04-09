/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.enemy;

import common.AISpawnPoint;
import common.Entity;
import data.GameData;
import data.World;
import entityparts.AnimationPart;
import entityparts.LifePart;
import entityparts.MovingPart;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import static enums.AITypes.MELEE;
import static enums.AITypes.SHOOTER;
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;

@ServiceProvider(service = IPluginService.class)

/**
 *
 * @author andreasmolgaard-andersen
 */
public class EnemyPlugin implements IPluginService {

    private int shooterLife = 1;
    private int meleeLife = 4;
    private String enemyType;

    @Override
    public void start(GameData gameData, World world) {
        for (AISpawnPoint spawnPoint : world.getSpawnPoints()) {
            if (spawnPoint.getAIType() == SHOOTER) {
                PositionPart enPos = spawnPoint.getEntity().getPart(PositionPart.class);
                Enemy enemy = new Enemy(enPos.getX(), enPos.getY());
                enemy.add(enPos);
                enemy.add(spawnPoint.getEntity().getPart(PropertiesPart.class));
                enemy.add(new LifePart(shooterLife));
                enemy.add(new MovingPart(10, 100, 175));
                enemyType = "Trooper";
                enemy.add(new AnimationPart("TrooperIdle", 1, getPath()));
                enemy.setAIType(SHOOTER);
                world.addEntity(enemy);
            }
            if (spawnPoint.getAIType() == MELEE) {
                PositionPart enPos = spawnPoint.getEntity().getPart(PositionPart.class);
                Enemy enemy = new Enemy(enPos.getX(), enPos.getY());
                enemy.add(enPos);
                enemy.add(spawnPoint.getEntity().getPart(PropertiesPart.class));
                enemy.add(new LifePart(meleeLife));
                enemy.add(new MovingPart(10, 100, 175));
                enemyType = "Raider";
                enemy.add(new AnimationPart("RaiderIdle", 6, getPath()));
                enemy.setAIType(MELEE);
                world.addEntity(enemy);
            }
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Enemy.class)) {
            world.removeEntity(entity);
        }
    }

    @Override
    public String getPath() {
        return EnemyPlugin.class.getResource("/sprites/" + enemyType + ".txt").getPath();
    }

}
