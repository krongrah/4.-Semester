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
import enums.CollisionTypes;
import org.openide.util.lookup.ServiceProvider;
import services.IPostProcessor;

/**
 *
 * @author Sebas
 */
@ServiceProvider(service = IPostProcessor.class)

public class Collision implements IPostProcessor {

    /**
     * Processes the world of the game to identify Entity collision
     *
     * @param gameData
     * @param world
     */
    @Override
    public void process(GameData gameData, World world) {
        for (Entity object : world.getEntities()) {
            PositionPart objPos = object.getPart(PositionPart.class);
            PropertiesPart objProp = object.getPart(PropertiesPart.class);

            if (!objProp.isSolid()) {
                //If object is not ground or wall:
                for (Entity target : world.getEntities()) {
                    PositionPart tarPos = target.getPart(PositionPart.class);
                    PropertiesPart tarProp = target.getPart(PropertiesPart.class);

                    if ((objPos.getX() + objProp.getWidth()) <= (tarPos.getX() + tarProp.getWidth())) {
                        if ((objPos.getY() + objProp.getHeight()) <= (tarPos.getY() + tarProp.getHeight())) {
                            setCollision(object, target);
                        }
                    }
                }
            }
        }
    }

    /**
     * Sets the entities to currently be hit
     *
     * @param target
     * @param object
     */
    private void setCollision(Entity object, Entity target) {
        LifePart tarLife = target.getPart(LifePart.class);
        LifePart objLife = object.getPart(LifePart.class);
        PropertiesPart tarProp = target.getPart(PropertiesPart.class);

        if (tarProp.isObstacle()) {
            object.setCollision(CollisionTypes.SOLIDOBJECT);
        } else {
            //Colliding with enemy, player or bullet:
            tarLife.setIsHit(true);
            objLife.setIsHit(true);
        }
    }
}
