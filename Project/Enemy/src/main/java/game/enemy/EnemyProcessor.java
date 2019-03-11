/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.enemy;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.MovingPart;
import service.IProcessor;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class EnemyProcessor implements IProcessor {

    @Override
    public void process(GameData gameData, World world) {
        
        
        for (Entity enemy : world.getEntities(Enemy.class)) {

            //Gets the parts of the enemy
            MovingPart mp = enemy.getPart(MovingPart.class);

            ////Implement method to move the player here////
            
            //Now processes the movement of the enemy
            mp.process(gameData, enemy);
            
        }
        
        
    }
    
}
