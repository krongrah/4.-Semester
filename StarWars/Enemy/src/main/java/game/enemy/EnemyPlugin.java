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
import Animation.Animation;
import java.io.IOException;
import org.openide.util.Exceptions;

@ServiceProvider(service = IPluginService.class)

/**
 *
 * @author andreasmolgaard-andersen
 */
public class EnemyPlugin implements IPluginService {

    private int shooterLife = 1;
    private int meleeLife = 4;
    private String enemyType;

    List<Animation> list;
    
    @Override
    public void start(GameData gameData, World world) {
        
        try {
            list = new ArrayList();
            list.add(new Animation(EnemyPlugin.class.getResource("sprites/RaiderAttack0.png").getPath().substring(5), "RaiderAttack", 1));
            list.add(new Animation(EnemyPlugin.class.getResource("sprites/RaiderIdle0.png").getPath().substring(5), "RaiderIdle", 1));
            list.add(new Animation(EnemyPlugin.class.getResource("sprites/RaiderWalking0.png").getPath().substring(5), "RaiderWalking", 1));
            list.add(new Animation(EnemyPlugin.class.getResource("sprites/TrooperIdle0.png").getPath().substring(5), "TrooperIdle", 1));
            list.add(new Animation(EnemyPlugin.class.getResource("sprites/TrooperShooting0.png").getPath().substring(5), "TrooperShooting", 1));
            list.add(new Animation(EnemyPlugin.class.getResource("sprites/TrooperWalking0.png").getPath().substring(5), "TrooperWalking", 1));

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        for (AISpawnPoint spawnPoint : world.getSpawnPoints()) {
            if (spawnPoint.getAIType() == SHOOTER) {
                PositionPart enPos = spawnPoint.getPos();
                Enemy enemy = new Enemy(enPos.getX(), enPos.getY());
                enemy.add(enPos);
                enemy.add(spawnPoint.getProp());
                enemy.add(new LifePart(shooterLife));
                enemy.add(new MovingPart(10, 100, 175));
//                System.out.println(EnemyPlugin.class.getResource("sprites/TrooperIdle0.png").getPath());
                enemy.add(new AnimationPart("TrooperIdle", 1, EnemyPlugin.class.getResource("sprites/TrooperIdle0.png").getPath().substring(5)));
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
                enemy.add(new AnimationPart("RaiderIdle", 1, EnemyPlugin.class.getResource("sprites/RaiderIdle0.png").getPath().substring(5)));
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
        
        return list;
    }

}
