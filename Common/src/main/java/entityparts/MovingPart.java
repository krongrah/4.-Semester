/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityparts;

import common.Entity;
import data.GameData;

/**
 *
 * @author ahmadhamid
 */
public class MovingPart implements EntityPart {

    private float dx;
    private float deceleration, acceleration;
    private float maxSpeed;
    private boolean left, right;

    public MovingPart(float deceleration, float acceleration, float maxSpeed) {
        this.deceleration = deceleration;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float dt = gameData.getDelta();

        // accelerating            
        if (left) {
            dx -=  acceleration * dt;
        }
        if (right) {
            dx +=  acceleration * dt;
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
