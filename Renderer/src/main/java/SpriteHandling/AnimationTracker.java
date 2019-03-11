/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpriteHandling;

import data.Entity;
import data.GameData;
import entityparts.PositionPart;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Krongrah
 */
public class AnimationTracker {

    private Map<Entity, Animation> map;

    public AnimationTracker() {
        this.map = new HashMap();
    }

    String getPos(Entity entity) {
        if (map.containsKey(entity)) {
            return map.get(entity).getCurrentPose();
        }
        map.put(entity, new Animation());
        return "Stand";
    }

    public void update(GameData gameData) {

        Iterator<Map.Entry<Entity, Animation>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Entity, Animation> entry = iterator.next();
            if(entry.getValue().isExpired()){
            iterator.remove();
            }else{
            entry.getValue().updateDelta(gameData.getDelta());
            PositionPart pos=((PositionPart) entry.getKey().getPart(PositionPart.class));
            entry.getValue().setMoving(pos.getX(), pos.getY());
            }
        }

    }
    
}
