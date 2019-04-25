/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entityparts.PositionPart;
import entityparts.PropertiesPart;
import enums.AITypes;

/**
 *
 * @author Sebas
 */
public class AISpawnPoint {

    private AITypes AIType;
    private PositionPart pos;
    private PropertiesPart prop;

    public AISpawnPoint(PositionPart pos, PropertiesPart prop, AITypes aiTypes) {
        this.pos = pos;
        this.prop = prop;
        this.AIType = aiTypes;
    }

    public PositionPart getPos() {
        return pos;
    }

    public PropertiesPart getProp() {
        return prop;
    }

    public AITypes getAIType() {
        return AIType;
    }

}
