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
import entityparts.SoundPart;
import entityparts.WeaponPart;
import enums.CollisionTypes;
import enums.Directions;
import java.io.File;
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
    }

    private void attack(Entity entity, GameData gameData, World world) {
        WeaponPart weapon = entity.getPart(WeaponPart.class);
        PositionPart pos = entity.getPart(PositionPart.class);
        PropertiesPart prop = entity.getPart(PropertiesPart.class);

        if (weapon.getCooldown() > 0) {
            weapon.setCooldown(weapon.getCooldown() - gameData.getDelta());
        }
        if (weapon.getCooldown() <= 0 && weapon.isAttacking()) {
            weapon.setCooldown(0.500f);
            Bullet b = new Bullet();
            MovingPart mp = new MovingPart(0, 1000, 50);

            PositionPart pp = new PositionPart(pos.getX() + prop.getWidth(), pos.getY());
            b.add(pp);
            b.add(mp);
            b.add(new LifePart(1));
            b.add(new SoundPart(new File(WeaponPlugin.class.getResource("/sounds/shot.mp3").getFile())));
            
            playSound(b);
            b.add(new PropertiesPart(5, 3, CollisionTypes.DAMAGE, false));
//            b.add(new AnimationPart("Luke", 1, "/Users/andreasmolgaard-andersen/Documents/GitHub/4.-Semester/StarWars/Player/target/classes/sprites/"));
            ///Users/andreasmolgaard-andersen/Documents/GitHub/4.-Semester/StarWars/Player/target/classes/sprites/
            b.add(new AnimationPart("bullet", 0, WeaponPlugin.class.getResource("sprites/bullet0.png").getPath().substring(5)));

            world.addEntity(b);

            if (pos.getDirection().equals(Directions.RIGHT)) {
                mp.setRight(true);
            } else {
                mp.setLeft(true);
                pp.setX(pos.getX() - prop.getWidth());
            }


    }
    }
    
    public void playSound(Entity entity){
        SoundPart sp = entity.getPart(SoundPart.class);
        if(sp !=null){
        sp.startPlaying();
     }
    }

        }

