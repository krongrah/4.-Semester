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
import org.openide.util.lookup.ServiceProvider;
import services.IPostProcessor;

@ServiceProvider(service = IPostProcessor.class)

/**
 *
 * @author Sebas
 */
public class PlayerPostProcessor implements IPostProcessor {

    private long lastHit = 0;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            LifePart lp = player.getPart(LifePart.class);
            if (lp.isHit()) {
                //Take damage:
                if (System.currentTimeMillis() >= (lastHit + lp.getImmunityTime())) {
                    lp.decreaseLife(1);
                    lp.setIsHit(false);
                    lastHit = System.currentTimeMillis();
                }
            }
            if (lp.getLife() <= 0) {
                world.removeEntity(player);
            }
        }
    }
}
