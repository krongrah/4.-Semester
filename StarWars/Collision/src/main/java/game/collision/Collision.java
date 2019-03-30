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
import enums.Directions;
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

            if (!objProp.isObstacle()) {
                for (Entity target : world.getEntities()) {
                    PositionPart tarPos = target.getPart(PositionPart.class);
                    PropertiesPart tarProp = target.getPart(PropertiesPart.class);
                    if (object != target && tarProp.isSolid()) {

                        //System.out.println("X-Distance, Right: " + dxR + " Left: " + dxL);
                        if (objPos.getX() <= tarPos.getX()) {
                            //Check for right side collision exclusively                        
                            float dxR = Math.abs((tarPos.getX() - objPos.getX()) + ((tarProp.getWidth() / 2) - (objProp.getWidth() / 2)));

                            if (dxR < objProp.getWidth()) {
                                if (!tarProp.isObstacle()) {
                                    //Hit by bullet or run into enemy:
                                    LifePart oLife = object.getPart(LifePart.class);
                                    LifePart tLife = target.getPart(LifePart.class);

                                    oLife.setIsHit(true);
                                    tLife.setIsHit(true);
                                }
                                //Collision detected:
                                object.setCollisionDirection(Directions.RIGHT);
                                object.setCollision(tarProp.getCollisionType());//NO_EFFECT or DAMAGE
                            }
                        }
                        if (objPos.getX() >= tarPos.getX()) {
                            float dxL = Math.abs((objPos.getX() - tarPos.getX()) - ((objProp.getWidth() / 2) + tarProp.getWidth() / 2));

                            //Check for left side collision exclusively
                            if (dxL <= objProp.getWidth()) {
                                if (!tarProp.isObstacle()) {
                                    //Hit by bullet or run into enemy:
                                    LifePart oLife = object.getPart(LifePart.class);
                                    LifePart tLife = target.getPart(LifePart.class);

                                    oLife.setIsHit(true);
                                    tLife.setIsHit(true);
                                }
                                //Collision detected:
                                object.setCollisionDirection(Directions.LEFT);
                                object.setCollision(tarProp.getCollisionType()); //NO_EFFECT or DAMAGE
                            }
                        }

//                        if ((objPos.getY() + objProp.getHeight()) > (tarPos.getY() + tarProp.getHeight())) {
//                            //setYAxisCollision(object, target);
//
//                        }
                    }
                }
            }
        }
    }
}
