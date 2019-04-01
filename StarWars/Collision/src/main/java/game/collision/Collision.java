/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.collision;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import enums.CollisionTypes;
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
                                //Collision detected:
                                object.setCollisionDirection(Directions.RIGHT);
                                object.setCollision(tarProp.getCollisionType());//NO_EFFECT or DAMAGE
                            }
                        }
                        if (objPos.getX() >= tarPos.getX()) {
                            float dxL = Math.abs((objPos.getX() - tarPos.getX()) - ((objProp.getWidth() / 2) + tarProp.getWidth() / 2));

                            //Check for left side collision exclusively
                            if (dxL <= objProp.getWidth()) {
                                //Collision detected:
                                object.setCollisionDirection(Directions.LEFT);
                                object.setCollision(tarProp.getCollisionType()); //NO_EFFECT or DAMAGE
                            }
                        }

                    }
                }
            }
        }
    }

    /**
     * Sets a specific collision for the object only in regards to the y-axis
     *
     * @param object : Any Entity that moves
     * @param target : Any obstacle
     */
    private void setYAxisCollision(Entity object, Entity target) {
        PropertiesPart tarProp = target.getPart(PropertiesPart.class);
        PositionPart objPos = object.getPart(PositionPart.class);
        PositionPart tarPos = target.getPart(PositionPart.class);

        object.setCollision(CollisionTypes.SOLIDOBJECT);
        if (tarProp.isObstacle()) {
            //Entity has bumped into obstacle on the y-axis:
            if (tarPos.getY() < objPos.getY()) {
                //Roof collision:
                object.setCollisionDirection(Directions.UP);
            } else {
                //Floor collision:
                object.setCollisionDirection(Directions.DOWN);
            }

        }
    }

    /**
     * Sets a specific collision for the object only in regards to the x-axis
     *
     * @param object : Any Entity that moves
     * @param target : Any obstacle
     */
    private void setXAxisCollision(Entity object, Entity target) {
        PropertiesPart tarProp = target.getPart(PropertiesPart.class);
        PositionPart objPos = object.getPart(PositionPart.class);
        PositionPart tarPos = target.getPart(PositionPart.class);

        object.setCollision(CollisionTypes.SOLIDOBJECT);
        if (tarProp.isObstacle()) {
            //Entity has bumped into obstacle on the x-axis:
            if (tarPos.getX() < objPos.getX()) {
                //Right collision:
                object.setCollisionDirection(Directions.RIGHT);
            } else {
                //Left collision:
                object.setCollisionDirection(Directions.LEFT);
            }
        }
    }

    private void resetEntityCollision(Entity object) {
        System.out.println("Resetting Collision");
        object.setCollision(CollisionTypes.NO_EFFECT);
        object.setCollisionDirection(null);
    }
}
