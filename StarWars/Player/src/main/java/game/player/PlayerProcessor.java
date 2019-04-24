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
import static data.GameKeys.SPACE;
import entityparts.AnimationPart;
import entityparts.MovingPart;
import entityparts.PositionPart;
import entityparts.SelfDestructPart;
import entityparts.SoundPart;
import entityparts.WeaponPart;
import java.io.File;
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
            WeaponPart wp = player.getPart(WeaponPart.class);
            AnimationPart ap = player.getPart(AnimationPart.class);
            

            //Sets whether the player is going left or right
            mp.setLeft(gameData.getKeys().isDown(A));
            
            //Adding sounds through entities
            if(gameData.getKeys().isDown(A)){
                Entity explosion = new Entity();
                explosion.add(new SelfDestructPart(0));
                SoundPart sp = new SoundPart(new File(PlayerPlugin.class.getResource("/sounds/explosion.mp3").getFile()));
                explosion.add(sp);
                sp.startPlaying();
                world.addEntity(explosion);
                
            }
                
            
            mp.setRight(gameData.getKeys().isDown(D));
            //adding sounds through world
            if(gameData.getKeys().isDown(D)){
                world.addSound(new File(PlayerPlugin.class.getResource("/sounds/jump.mp3").getFile()));

                
            }
            
            wp.setAttacking(gameData.getKeys().isDown(SPACE));


            

            //Now processes the movement of the player based on the keys
            if (mp.isAccelerating()) {
                ap.changeAnimation("Lukewalking", 7);
            } else {
                ap.changeAnimation("Lukeidle", 5);
            }

            gameData.setFocusX(pp.getX());
            gameData.setFocusY(pp.getY());

        }

    }

}
