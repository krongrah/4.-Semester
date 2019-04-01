/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityparts;

import common.Entity;
import data.GameData;

/**
 *
 * @author Krongrah
 */
public class WeaponPart implements EntityPart {

    private boolean attacking = false;
    private float cooldown = 0f;

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isAttacking() {
        return attacking;
    }

}
