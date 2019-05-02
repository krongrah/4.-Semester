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
import entityparts.WeaponPart;
import static enums.AITypes.MELEE;
import static enums.AITypes.SHOOTER;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;
import sprites.Animation;

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
                PositionPart enPos = spawnPoint.getPos();
                Enemy enemy = new Enemy(enPos.getX(), enPos.getY());
                enemy.add(enPos);
                enemy.add(spawnPoint.getProp());
                enemy.add(new LifePart(shooterLife));
                enemy.add(new MovingPart(10, 100, 175));
                enemy.add(new AnimationPart("TrooperIdle", 1, EnemyPlugin.class.getResource("/sprites/").getPath()));
                enemy.add(new WeaponPart());
                enemy.setAIType(SHOOTER);
                world.addEntity(enemy);
            }
            if (spawnPoint.getAIType() == MELEE) {
                PositionPart enPos = spawnPoint.getPos();
                Enemy enemy = new Enemy(enPos.getX(), enPos.getY());
                enemy.add(enPos);
                enemy.add(spawnPoint.getProp());
                enemy.add(new LifePart(meleeLife));
                enemy.add(new MovingPart(10, 100, 175));
                enemy.add(new AnimationPart("RaiderIdle", 6, EnemyPlugin.class.getResource("/sprites/").getPath()));
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
    public List<Animation> getAnimation() {
        List<Animation> list = new ArrayList();
        list.add(new Animation(EnemyPlugin.class.getResource("/sprites/").getPath(), "RaiderAttack", 6));
        list.add(new Animation(EnemyPlugin.class.getResource("/sprites/").getPath(), "RaiderIdle", 7));
        list.add(new Animation(EnemyPlugin.class.getResource("/sprites/").getPath(), "RaiderWalking", 7));
        return list;

    }

}
