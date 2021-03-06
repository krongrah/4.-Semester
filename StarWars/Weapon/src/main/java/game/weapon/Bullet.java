/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.weapon;

import common.Entity;
import interfaces.Projectile;

/**
 *
 * @author Sebas
 */
public class Bullet extends Entity implements Projectile {

    private float duration = 5;

    public void lowerDuration(float delta) {
        duration -= delta;
    }

    public boolean isExpired() {
        return duration <= 0;
    }

}
