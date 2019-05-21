/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import common.AISpawnPoint;
import common.Entity;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
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
    
    private HashSet<Entity> deletionSet = new HashSet<>();
    
    private LinkedList<File> soundList = new LinkedList<>();

    /**
     * A list of all enemy spawn points read from the map file
     */
    private final List<AISpawnPoint> enemySpawnPoints = new ArrayList();

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
     * Adds the entity to the list that removes it from the world later
     * To avoid concurrent access issues.
     * Adds a specific enemy spawn point to the list of spawn points
     * @param spawn 
     */
    public void addEnemySpawn(AISpawnPoint spawn) {
        enemySpawnPoints.add(spawn);
    }

    /**
     * Removes the given Entity from the world
     * @param entity Is the entity to be removed
     */
    public void removeEntity(Entity entity) {
        deletionSet.add(entity);
        
    }
    //The actual deletion
    public void performDeletion(){
        for (Entity e : deletionSet){
            entityMap.remove(e.getID());
        }
        deletionSet.clear();
    }

    /**
     * Returns a collection of all Entities
     *
     * @return
     */
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }
    
    public List<AISpawnPoint> getSpawnPoints(){
        return this.enemySpawnPoints;
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

    public void addSound(File sound) {
        soundList.add(sound);
    }
    
    public void clearSoundList(){
        soundList = new LinkedList<>();
    }
    
    public LinkedList<File> getSoundList(){
        return soundList;
    }

}
