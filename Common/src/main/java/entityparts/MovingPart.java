/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityparts;

import common.Entity;
import data.GameData;
import data.GameKeys;
import static data.GameKeys.A;
import static data.GameKeys.D;

/**
 * Class is used for Entities to able In order to be able to move
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public class MovingPart implements EntityPart {

    private float dx;
    private float deceleration, acceleration;
    private float maxSpeed;
    private boolean left, right;


    /**
     * Is the constructor of the MovingPart
     * @param deceleration Sets the deceleration
     * @param acceleration Sets the acceleration
     * @param maxSpeed Sets the maxSpeed
     */
    public MovingPart(float deceleration, float acceleration, float maxSpeed) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
    }

    /**
     * Used to set the Entity left
     *
     * @param left Is the boolean to set the right value to
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Used to set the Entity right
     *
     * @param right Is the boolean to set the right value to
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Processes the MovingPart by updating the PositionPart and accelleration
     *
     * @param gameData Is used to retrieve the time before last update
     * @param entity Is used to get the Positionpart to update
     */
    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float dt = gameData.getDelta();

        // accelerating            
        if (gameData.getKeys().isDown(A)) {
            dx -= acceleration * dt;
        }
        if (gameData.getKeys().isDown(D)) {
            dx += acceleration * dt;
        }

        // deccelerating
        if (dx > 0) {
            dx -= dx * deceleration * dt;
        }
        if (dx < 0) {
            dx += dx * deceleration * dt;
        }

        if (dx > maxSpeed) {
            dx = maxSpeed;
        }
        if (dx < -maxSpeed) {
            dx = -maxSpeed;
        }

        // set position
        x += dx * dt;
        positionPart.setX(x);
    }
}
