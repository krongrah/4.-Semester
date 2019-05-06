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
import entityparts.WeaponPart;
import enums.CollisionTypes;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;
import Animation.Animation;

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
        player.add(new PositionPart(19*32, (39*32)+16));
        player.add(new MovingPart(10, 175, 250));
        player.add(new WeaponPart());
        player.add(new AnimationPart("Luke", 1, PlayerPlugin.class.getResource("/sprites/").getPath()));

        world.addEntity(player);
        System.out.println("starting");
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    @Override
    public List<Animation> getAnimation() {
        List<Animation> list = new ArrayList();
        list.add(new Animation(PlayerPlugin.class.getResource("/sprites/").getPath(), "Luke", 1));
        return list;
    }

}
