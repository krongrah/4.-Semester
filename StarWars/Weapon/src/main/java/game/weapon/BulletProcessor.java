/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.weapon;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.MovingPart;
import org.openide.util.lookup.ServiceProvider;
import services.IProcessor;

/**
 *
 * @author Sebas
 */
@ServiceProvider(service = IProcessor.class)

public class BulletProcessor implements IProcessor {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            MovingPart mp = bullet.getPart(MovingPart.class);
                        
            mp.process(gameData, bullet);
        }
    }

}
