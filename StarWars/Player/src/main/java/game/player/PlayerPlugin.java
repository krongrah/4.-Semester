/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;
import Animation.Animation;
import java.io.IOException;
import org.openide.util.Exceptions;

@ServiceProvider(service = IPluginService.class)

/**
 *
 * @author andreasmolgaard-andersen
 */
public class PlayerPlugin implements IPluginService {
    
    List<Animation> list = new ArrayList();

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = new Player();

        player.add(new PropertiesPart(22, 32, CollisionTypes.SOLIDOBJECT,false));
        player.add(new LifePart(3));
        player.add(new PositionPart(19*32, (39*32)+16));
        player.add(new MovingPart(10, 175, 250));
        player.add(new WeaponPart());

        //player.add(new AnimationPart("Lukeidle", 5, getPath()));
        //player.add(new SoundPart(new File(PlayerPlugin.class.getResource("/sounds/Shot.mp3").getFile())));

//        player.add(new AnimationPart("Luke", 1, PlayerPlugin.class.getResource("sprites/Luke0.png").getPath()));


        try {
            List<Animation> list = new ArrayList();
            list.add(new Animation(PlayerPlugin.class.getResource("sprites/Luke0.png").getPath().substring(5), "Luke", 1));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        player.add(new AnimationPart("Luke", 1, PlayerPlugin.class.getResource("sprites/Luke0.png").getPath().substring(5)));

        
        world.addEntity(player);
        System.out.println("starting");
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    @Override
    public List<Animation> getAnimation() {

            return list;

    }

}
