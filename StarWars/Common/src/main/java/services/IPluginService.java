/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import data.GameData;
import data.World;

/**
 * Interface used to facilitate the starting and stopping of entities that
 * implements the interface
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public interface IPluginService {

    /**
     * Has the responsibility to start the Entity that implements this interface
     *
     * @param gameData Is used to get data used to start the Entity
     * @param world Is used to but the Entity in for world
     */
    void start(GameData gameData, World world);

    /**
     * Stops and removes the Entity that implements this interface
     *
     * @param gameData Is used to get data needed to stop the Entity
     * @param world Is used to remove the Entity from the world
     */
    void stop(GameData gameData, World world);
    
        String getPath();

    
}
