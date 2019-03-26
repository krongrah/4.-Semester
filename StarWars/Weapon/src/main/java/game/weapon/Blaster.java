/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.weapon;

import entityparts.LifePart;
import entityparts.MovingPart;
import entityparts.PositionPart;
import interfaces.IWeapon;

/**
 *
 * @author Sebas
 */
public class Blaster implements IWeapon {
    
    /**
     * The Attack of the specific Weapon type, here Blaster is the type, this attack spawns a bullet.
     * @param pos Attackers PositionsPart
     */
    @Override
    public void attack(PositionPart pos) {
        Bullet bullet = new Bullet();
        bullet.add(new PositionPart(pos.getX(), pos.getY()));
        bullet.add(new MovingPart(10, 25, 50));
        bullet.add(new LifePart(1));
    }
    
}
