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

    private AITypes AIType;

    public Enemy() {
    }

    public AITypes getAIType() {
        return AIType;
    }

    public void setAIType(AITypes AIType) {
        this.AIType = AIType;
    }
}
