/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entityparts.EntityPart;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author ahmadhamid
 */
public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private float radius;
    private Map<Class, EntityPart> parts;

    public Entity(float r) {
        parts = new ConcurrentHashMap<>();
        this.radius = r;
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

    public float getRadius() {
        return radius;
    }

    public String getID() {
        return ID.toString();
    }

}
