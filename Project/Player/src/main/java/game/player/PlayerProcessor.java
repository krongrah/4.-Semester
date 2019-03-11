/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import common.Entity;
import data.GameData;
import data.World;
import static data.GameKeys.A;
import static data.GameKeys.D;
import entityparts.MovingPart;
import service.IProcessor;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class PlayerProcessor implements IProcessor {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {

            //Gets the parts of the player
            MovingPart mp = player.getPart(MovingPart.class);

            //Sets whether the player is going left or right
            mp.setLeft(gameData.getKeys().isDown(A));
            mp.setRight(gameData.getKeys().isDown(D));

            //Now processes the movement of the player based on the keys
            mp.process(gameData, player);
            
        }

    }

}