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

        player.add(new PropertiesPart(32, 32, Sprites.LUKE, true));
        player.add(new LifePart(3));
        player.add(new PositionPart(18 * 32, 9 * 32));
        player.add(new MovingPart(10, 175, 250));
        player.add(new AnimationPart("Saber", 0, getPath()));
        
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    @Override
    public String getPath() {
        return PlayerPlugin.class.getResource("/sprites/test.txt").getPath();
    }

}
