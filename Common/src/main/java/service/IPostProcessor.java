/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import data.GameData;
import data.World;

/**
 * Facilitates the ability to process entities
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public interface IPostProcessor {

    /**
     * Has the responsibility of processing the Entity
     *
     * @param gameData Is used to get data that is needed for the Entity to
     * process
     * @param world Is used if the Entity manipulates the world
     */
    void process(GameData gameData, World world);
}
