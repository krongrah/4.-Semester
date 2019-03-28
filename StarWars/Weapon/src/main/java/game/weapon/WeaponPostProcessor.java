/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.weapon;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.LifePart;
import org.openide.util.lookup.ServiceProvider;
import services.IPostProcessor;

/**
 *
 * @author Sebas
 */

@ServiceProvider(service = IPostProcessor.class)

public class WeaponPostProcessor implements IPostProcessor {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            LifePart lp = bullet.getPart(LifePart.class);

            if(lp.isHit()){
                world.removeEntity(bullet);
            }
        }
    }

}
