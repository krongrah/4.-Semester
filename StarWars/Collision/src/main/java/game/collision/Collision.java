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
import static enums.CollisionTypes.SOLIDOBJECT;
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
                    if (object != target && tarProp.getCollisionType() == CollisionTypes.SOLIDOBJECT && objProp.getCollisionType() == CollisionTypes.SOLIDOBJECT) {

                        //System.out.println("X-Distance, Right: " + dxR + " Left: " + dxL);
                        if (objPos.getX() < tarPos.getX()) {
                            //Check for right side collision exclusively                        
                            float dxR = (tarPos.getX() - objPos.getX()) - (tarProp.getWidth() / 2 + objProp.getWidth() / 2);
                            if (dxR < 0) {
                                //Collision detected:
                                collide(object, target, Directions.RIGHT);
                            }
                        }
                        if (objPos.getX() > tarPos.getX()) {
                            float dxL = (objPos.getX() - tarPos.getX()) - (objProp.getWidth() / 2 + tarProp.getWidth() / 2);
                            //Check for left side collision exclusively
                            if (dxL < 0) {
                                //Collision detected:
                                //collide(object, target, Directions.LEFT);
                            }
                        }

                    }
                }
            }
        }
    }

    private void collide(Entity object, Entity target, Directions direction) {
        PropertiesPart targetProp = target.getPart(PropertiesPart.class);
        PositionPart objectPos = object.getPart(PositionPart.class);
        PropertiesPart objectProp = object.getPart(PropertiesPart.class);
        PositionPart targetPos = target.getPart(PositionPart.class);

        if (targetProp.getCollisionType() == SOLIDOBJECT) {
            switch (direction) {
                case LEFT:
                    objectPos.setX(targetPos.getX() + targetProp.getWidth() / 2 + objectProp.getWidth() / 2);
                    break;
                case RIGHT:
                    objectPos.setX(targetPos.getX() - targetProp.getWidth() / 2 - objectProp.getWidth() / 2);

                    break;

            }

        }

    }

}

//    /**
//     * Sets a specific collision for the object only in regards to the y-axis
//     *
//     * @param object : Any Entity that moves
//     * @param target : Any obstacle
//     */
//    private void setYAxisCollision(Entity object, Entity target) {
//        PropertiesPart tarProp = target.getPart(PropertiesPart.class);
//        PositionPart objPos = object.getPart(PositionPart.class);
//        PositionPart tarPos = target.getPart(PositionPart.class);
//
//        object.setCollision(CollisionTypes.SOLIDOBJECT);
//        if (tarProp.isObstacle()) {
//            //Entity has bumped into obstacle on the y-axis:
//            if (tarPos.getY() < objPos.getY()) {
//                //Roof collision:
//                object.setCollisionDirection(Directions.UP);
//            } else {
//                //Floor collision:
//                object.setCollisionDirection(Directions.DOWN);
//            }
//
//        }
//    }
//
//    /**
//     * Sets a specific collision for the object only in regards to the x-axis
//     *
//     * @param object : Any Entity that moves
//     * @param target : Any obstacle
//     */
//    private void setXAxisCollision(Entity object, Entity target) {
//        PropertiesPart tarProp = target.getPart(PropertiesPart.class);
//        PositionPart objPos = object.getPart(PositionPart.class);
//        PositionPart tarPos = target.getPart(PositionPart.class);
//
//        object.setCollision(CollisionTypes.SOLIDOBJECT);
//        if (tarProp.isObstacle()) {
//            //Entity has bumped into obstacle on the x-axis:
//            if (tarPos.getX() < objPos.getX()) {
//                //Right collision:
//                object.setCollisionDirection(Directions.RIGHT);
//            } else {
//                //Left collision:
//                object.setCollisionDirection(Directions.LEFT);
//            }
//        }
//    }
//
//    private void resetEntityCollision(Entity object) {
//        System.out.println("Resetting Collision");
//        object.setCollision(CollisionTypes.NO_EFFECT);
//        object.setCollisionDirection(null);
//    }
//}
