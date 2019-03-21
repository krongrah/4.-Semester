/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entityparts.PositionPart;

/**
 * Entities of the Weapon type implements this interface to differentiate it
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public interface IWeapon {

    /**
     * Is used to let the Weapons attack
     *
     * @param pos Is used to know where the attack is coming from
     */
    public void attack(PositionPart pos);

}
