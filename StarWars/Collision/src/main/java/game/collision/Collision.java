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
import interfaces.Projectile;
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

            if (objProp!= null && !objProp.isObstacle()) {
                //System.out.println(((MovingPart) (object.getPart(MovingPart.class))).isMoving());
                for (Entity target : world.getEntities()) {
                    PositionPart tarPos = target.getPart(PositionPart.class);
                    PropertiesPart tarProp = target.getPart(PropertiesPart.class);
                    if (tarProp == null) continue;
                    if (!object.equals(target) && tarProp.getCollisionType() == CollisionTypes.SOLIDOBJECT && objProp.getCollisionType() == CollisionTypes.SOLIDOBJECT) {
                        if (objPos.getX() < tarPos.getX()) {
                            //Check for right side collision exclusively                        
                            float dxR = (tarPos.getX() - objPos.getX()) - (tarProp.getWidth() / 2 + objProp.getWidth() / 2);
                            if (dxR < 0) {
                                //Collision detected:
                                collide(object, target, Directions.RIGHT);

                            }
                            if (objPos.getX() > tarPos.getX()) {
                                //float dxL = (objPos.getX() - tarPos.getX()) - (objProp.getWidth() / 2 + tarProp.getWidth() / 2);
                                //Check for left side collision exclusively
                                if ((objPos.getX() - (objProp.getWidth() / 2)) - ((tarProp.getWidth() / 2) + tarPos.getX()) <= 1) {
                                    collide(object, target, Directions.LEFT);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void collide(Entity object, Entity target, Directions direction) {
        //System.out.println("collide");
        PropertiesPart targetProp = target.getPart(PropertiesPart.class);
        PositionPart objectPos = object.getPart(PositionPart.class);
        PropertiesPart objectProp = object.getPart(PropertiesPart.class);
        PositionPart targetPos = target.getPart(PositionPart.class);
        MovingPart objectMov = object.getPart(MovingPart.class);
        LifePart objLife = object.getPart(LifePart.class);

        if (objectMov.isMoving()) {
            if (objectProp.getCollisionType() == CollisionTypes.DAMAGE) {
                //System.out.println("Object x: " + objectPos.getX() + " Target x: " + targetPos.getX());
                if (object instanceof Projectile) {
                    //It's a bullet
                    objLife.setIsHit(true);
                }
                if (target.hasPart(LifePart.class)) {
                    LifePart tarLife = target.getPart(LifePart.class);
                    tarLife.setIsHit(true);
                }
            }
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
