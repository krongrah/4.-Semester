/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import enums.AITypes;

/**
 *
 * @author Sebas
 */
public class AISpawnPoint {

    private AITypes AIType;
    private Entity entity;

    public AISpawnPoint(Entity entity, AITypes AIType) {
        this.AIType = AIType;
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public AITypes getAIType() {
        return AIType;
    }

}
