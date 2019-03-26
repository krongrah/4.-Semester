/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.weapon;

import data.GameData;
import data.World;
import entityparts.AnimationPart;
import entityparts.LifePart;
import entityparts.MovingPart;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;
import sprites.Sprites;

/**
 *
 * @author Sebas
 */

@ServiceProvider(service = IPluginService.class)


public class BulletPlugin implements IPluginService {

    @Override
    public void start(GameData gameData, World world) {
        Bullet bullet = new Bullet();
        bullet.add(new PropertiesPart(5, 3, Sprites.BULLET, false));
        bullet.add(new LifePart(1));
        bullet.add(new PositionPart(19 * 32, 39 + 16 * 32));
        bullet.add(new MovingPart(10, 25, 50));
        MovingPart mp = bullet.getPart(MovingPart.class);
        mp.setRight(true); 
        bullet.add(new AnimationPart("Bullet", 0, getPath()));
        
        world.addEntity(bullet);
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPath() {
        return BulletPlugin.class.getResource("/sprites/bullet.txt").getPath();
    }
    
}
