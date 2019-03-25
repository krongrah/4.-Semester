/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collision;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.LifePart;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import services.IPostProcessor;

/**
 *
 * @author Sebas
 */
public class Collision implements IPostProcessor {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity object : world.getEntities()) {
            PositionPart objPos = object.getPart(PositionPart.class);
            PropertiesPart objProp = object.getPart(PropertiesPart.class);
            
            for (Entity target : world.getEntities()) {
                PositionPart tarPos = target.getPart(PositionPart.class);
                PropertiesPart tarProp = target.getPart(PropertiesPart.class);

                if ((objPos.getX() + objProp.getWidth()) <= (tarPos.getX() + tarProp.getWidth())) {
                    if ((objPos.getX() + objProp.getHeight()) <= (tarPos.getY() + tarProp.getHeight())) {
                        setCollision(object, target);
                    }
                }
            }
        }
    }

    private void setCollision(Entity e1, Entity e2) {
        LifePart tarLife = e1.getPart(LifePart.class);
        LifePart objLife = e2.getPart(LifePart.class);

        tarLife.setIsHit(true);
        objLife.setIsHit(true);
    }
}
