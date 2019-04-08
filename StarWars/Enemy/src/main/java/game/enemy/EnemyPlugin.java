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
import enums.AITypes;
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

    private Enemy enemy;
    private int shooterLife = 2;
    private int meleeLife = 1;
    private String enemyType;

    @Override
    public void start(GameData gameData, World world) {
        for (AISpawnPoint spawnPoint : world.getSpawnPoints()) {
            if (spawnPoint.getAIType() == SHOOTER) {
                Entity enemy = spawnPoint.getEntity();
                enemy.add(new LifePart(shooterLife));
                enemy.add(new MovingPart(10, 175, 250));
                enemyType = "Trooper";
                enemy.add(new AnimationPart("TrooperIdle", 1, getPath()));
                world.addEntity(enemy);
            }
            if (spawnPoint.getAIType() == MELEE) {
                Entity enemy = spawnPoint.getEntity();
                enemy.add(new LifePart(meleeLife));
                enemy.add(new MovingPart(10, 175, 250));
                enemyType = "Raider";
                enemy.add(new AnimationPart("RaiderIdle", 7, getPath()));
                world.addEntity(enemy);
            }
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }

    @Override
    public String getPath() {
        return EnemyPlugin.class.getResource("/sprites/" + enemyType + ".txt").getPath();
    }

}
