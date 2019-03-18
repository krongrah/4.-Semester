/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import common.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class is used to hold all entities of the game
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public class World {

    /**
     * Is a HashMap that holds all entities and their ids It it possible to get
     * the Entities by giving an ID
     */
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    /**
     * Adds the given Entity to the world
     *
     * @param entity Is the entity to be added
     * @return the String ID of the Entity added
     */
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    /**
     * Removes the given Entity from the world
     *
     * @param entity Is the entity to be removed
     */
    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    /**
     * Returns a collection of all Entities
     *
     * @return
     */
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    /**
     * Returns a list of entities
     *
     * @param <E> Is the object E of type Entities to find
     * @param entityTypes Is a list of all the entityTypes
     * @return
     */
    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

}
