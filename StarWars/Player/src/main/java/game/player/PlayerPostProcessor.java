/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.LifePart;
import enums.CollisionTypes;
import services.IPostProcessor;

/**
 *
 * @author Sebas
 */
public class PlayerPostProcessor implements IPostProcessor {
    
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            LifePart lp = player.getPart(LifePart.class);
            if (player.getCollisionType() == CollisionTypes.DAMAGE) {
                //Take damage:
                lp.decreaseLife(1);
            }
            if (lp.getLife() <= 0) {
                world.removeEntity(player);
            }
        }
    }
}
