/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entityparts.EntityPart;
import enums.CollisionTypes;
import enums.Directions;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author ahmadhamid
 */
public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private Map<Class, EntityPart> parts;

    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }

    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    public String getID() {
        return ID.toString();
    }

    public boolean hasPart(Class partClass) {
        return parts.containsKey(partClass);
    }

    public Collection<EntityPart> getAllParts() {
        return parts.values();
    }

}
