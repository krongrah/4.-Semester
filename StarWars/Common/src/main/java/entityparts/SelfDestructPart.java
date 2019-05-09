/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityparts;

import common.Entity;
import data.GameData;

/**
 *Use this in an entity to determine how many frames it should live
 * @author Naik
 */
public class SelfDestructPart implements EntityPart {
    private int framesToLive;
    
    public SelfDestructPart(int framesToLive){
    this.framesToLive = framesToLive;
}

    @Override
    public void process(GameData gameData, Entity entity) {
        framesToLive--;
    }
    
    public boolean shouldDestroy(){
       return framesToLive >=0;
        
    }
}
