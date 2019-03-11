/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.player;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.LifePart;
import entityparts.MovingPart;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import services.IPluginService;
import sprites.Sprites;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class PlayerPlugin implements IPluginService {

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = new Player();

        player.add(new PropertiesPart(50, 100, Sprites.LUKE, true));
        player.add(new LifePart(3));
        player.add(new PositionPart(0, 0));
        player.add(new MovingPart(10, 100, 100));

        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

}
