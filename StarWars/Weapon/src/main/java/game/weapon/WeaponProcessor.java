/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.weapon;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.AnimationPart;
import entityparts.LifePart;
import entityparts.MovingPart;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import entityparts.WeaponPart;
import enums.Directions;
import org.openide.util.lookup.ServiceProvider;
import services.IProcessor;

/**
 *
 * @author Sebas
 */
@ServiceProvider(service = IProcessor.class)

public class WeaponProcessor implements IProcessor {

    @Override
    public void process(GameData gameData, World world) {

        //resolves all attacks
        for (Entity entity : world.getEntities()) {
            if (entity.hasPart(WeaponPart.class)) {
                attack(entity, gameData, world);
            }
        }

        //processes bullets
        for (Entity bullet : world.getEntities(Bullet.class)) {
            MovingPart mp = bullet.getPart(MovingPart.class);
            mp.process(gameData, bullet);
            System.out.println("bullet");
        }
        System.out.println("");
    }

    private void attack(Entity entity, GameData gameData, World world) {
        WeaponPart weapon = entity.getPart(WeaponPart.class);
        PositionPart pos = entity.getPart(PositionPart.class);
        PropertiesPart prop = entity.getPart(PropertiesPart.class);

        if (weapon.getCooldown() > 0) {
            weapon.setCooldown(weapon.getCooldown() - gameData.getDelta());
        }
        if (weapon.getCooldown() <= 0 && weapon.isAttacking()) {
            weapon.setCooldown(3);
            Bullet b = new Bullet();
            MovingPart mp = new MovingPart(0, 1000, 50);
            PositionPart pp = new PositionPart(pos.getX() + prop.getWidth() / 2, pos.getY());
            b.add(pp);
            b.add(mp);
            b.add(new LifePart(1));
            b.add(new PropertiesPart(5, 3, true));
            b.add(new AnimationPart("bullet", 0, getPath()));
            world.addEntity(b);
            
            if (pos.getDirection().equals(Directions.RIGHT)) {
                mp.setRight(true);
            } else {
                mp.setLeft(true);
                pp.setX(pos.getX() - prop.getWidth());
            }
            
        }

    }

    private String getPath() {
        return WeaponPlugin.class.getResource("/sprites/bullet.txt").getPath();
    }

}
