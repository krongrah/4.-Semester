/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityparts;

import common.Entity;
import data.GameData;

/**
 * An interface used to differentiate EntityPart's from other Objects
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public interface EntityPart {

    /**
     * Is an interface that allows all classes that implements the interface to
     * process
     *
     * @param gameData Is the data used to process
     * @param entity Is the Entity holding the EntityPart
     */
    void process(GameData gameData, Entity entity);
}
