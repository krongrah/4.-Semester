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
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;
import sprites.Sprites;

@ServiceProvider(service = IPluginService.class)

/**
 *
 * @author andreasmolgaard-andersen
 */
public class PlayerPlugin implements IPluginService {

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = new Player();

        player.add(new PropertiesPart(22, 32, CollisionTypes.SOLIDOBJECT,false));
        player.add(new LifePart(3));
        player.add(new PositionPart(22*32, (39*32)+16));
        player.add(new MovingPart(10, 175, 250));
        player.add(new WeaponPart());
        player.add(new AnimationPart("Lukeidle", 5, getPath()));
        //player.add(new SoundPart(new File(PlayerPlugin.class.getResource("/sounds/Shot.mp3").getFile())));

        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    @Override
    public String getPath() {
        return PlayerPlugin.class.getResource("/sprites/Luke.txt").getPath();
    }

}
