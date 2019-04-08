/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.enemy;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.LifePart;
import services.IPostProcessor;

/**
 *
 * @author Sebas
 */
public class EnemyPostProcessor implements IPostProcessor {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            LifePart life = enemy.getPart(LifePart.class);
            if(life.getLife() <= 0){
                world.removeEntity(enemy);
            }
        }
    }
    
}
