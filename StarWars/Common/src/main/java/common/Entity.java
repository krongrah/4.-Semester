/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import entityparts.EntityPart;
import enums.CollisionTypes;
import enums.Directions;
import interfaces.ICollision;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author ahmadhamid
 */
public class Entity implements Serializable, ICollision {

    private final UUID ID = UUID.randomUUID();
    private CollisionTypes colType;
    private Directions collisionDirection;

    public void setCollisionDirection(Directions collisionDirection) {
        this.collisionDirection = collisionDirection;
    }

    public Directions getCollisionDirection() {
        return collisionDirection;
    }

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

    @Override
    public CollisionTypes getCollisionType() {
        return this.colType;
    }

    @Override
    public void setCollision(CollisionTypes type) {
        this.colType = type;
    }

}
