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
import entityparts.AnimationPart;
import entityparts.MovingPart;
import entityparts.PositionPart;
import org.openide.util.lookup.ServiceProvider;
import services.IProcessor;

@ServiceProvider(service = IProcessor.class)

/**
 *
 * @author andreasmolgaard-andersen
 */
public class PlayerProcessor implements IProcessor {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {

            //Gets the parts of the player
            PositionPart pp = player.getPart(PositionPart.class);
            MovingPart mp = player.getPart(MovingPart.class);
            AnimationPart ap = player.getPart(AnimationPart.class);
            
            //Sets whether the player is going left or right
            mp.setLeft(gameData.getKeys().isDown(A));
            mp.setRight(gameData.getKeys().isDown(D));

            //Now processes the movement of the player based on the keys
            mp.process(gameData, player);

            
            gameData.setFocusX(pp.getX());
            gameData.setFocusY(pp.getY() + 50);

            System.out.println("Before +1 : " + ap.getFramesInCurrentAnimation());
            ap.setFramesInCurrentAnimation(ap.getFramesInCurrentAnimation() + 1);
            System.out.println("After +1 : " + ap.getFramesInCurrentAnimation());
            ap.process(gameData, player);
            
        }

    }

}
