/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import data.GameData;
import data.World;

/**
 * Facilitates the ability to process Entities that implements the interface
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public interface IProcessor {

    /**
     * Processes or updates the Entity
     *
     * @param gameData Is used to manipulate data
     * @param world Is used to manipulate the world
     */
    void process(GameData gameData, World world);
}
