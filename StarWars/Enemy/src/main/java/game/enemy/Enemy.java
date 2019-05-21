/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.enemy;

import enums.AITypes;
import common.Entity;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Enemy extends Entity {
    private float originX;
    private float originY;
    private AITypes AIType;

    public Enemy(float originX, float originY) {
        this.originX = originX;
        this.originY = originY;
    }

    public float getOriginX() {
        return originX;
    }

    public float getOriginY() {
        return originY;
    }

    public AITypes getAIType() {
        return AIType;
    }

    public void setAIType(AITypes AIType) {
        this.AIType = AIType;
    }
}
