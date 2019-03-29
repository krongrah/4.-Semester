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
import entityparts.MovingPart;
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

                        float dxL = ((objPos.getX() - tarPos.getX()) + (objProp.getWidth() + tarProp.getWidth()));
                        float dxR = ((tarPos.getX() - objPos.getX()) + ((tarProp.getWidth() / 2) - (objProp.getWidth() / 2)));
//                        System.out.println("Coordinates:");
//                        System.out.println("Object: (" + objPos.getX() + ", " + objPos.getY() + ")");
//                        System.out.println("Target: (" + tarPos.getX() + ", " + tarPos.getY() + ")");
                        System.out.println("X-Distance, Right: " + dxR + " Left: " + dxL);
//                        System.out.println("\nHeight x Width:");
//                        System.out.println("Object: " + objProp.getHeight() + " x " + objProp.getWidth());
//                        System.out.println("Target: " + tarProp.getHeight() + " x " + tarProp.getWidth());
//                        System.out.println("\n\n");

                        if (dxR <= 1 ) {
                            System.out.println("Right collision by " + dxR);
                            setXAxisCollision(object, target);
                        } else {
                            resetEntityCollision(object);
                        }
                        if ((objPos.getY() + objProp.getHeight()) > (tarPos.getY() + tarProp.getHeight())) {
                            //setYAxisCollision(object, target);

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
        PropertiesPart objProp = object.getPart(PropertiesPart.class);
        PositionPart objPos = object.getPart(PositionPart.class);
        PositionPart tarPos = target.getPart(PositionPart.class);

        object.setCollision(CollisionTypes.SOLIDOBJECT);

//        System.out.println("Coordinates:");
//        System.out.println("Player: (" + objPos.getX() + ", " + objPos.getY() + ")");
//        System.out.println("Player: (" + tarPos.getX() + ", " + tarPos.getY() + ")\n\n");
        if (tarProp.isObstacle()) {
            //Entity has bumped into obstacle on the x-axis:
            if ((objPos.getX() + objProp.getWidth() / 2) - (tarPos.getX() + tarProp.getWidth() / 2) <= 1) {
                //Right collision:
                object.setCollisionDirection(Directions.RIGHT);
            }
            if ((tarPos.getX() + tarProp.getWidth() / 2) < (objPos.getX() + objProp.getWidth() / 2)) {
                //Left collision:
                //object.setCollisionDirection(Directions.LEFT);
            }

        }
    }

    private void resetEntityCollision(Entity object) {
        object.setCollision(CollisionTypes.NO_EFFECT);
        object.setCollisionDirection(null);
    }
}
