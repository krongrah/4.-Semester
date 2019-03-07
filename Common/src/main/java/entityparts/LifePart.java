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
 * @author ahmadhamid
 */
public class LifePart implements EntityPart {

    private int life;
    private boolean isHit = false;

    public LifePart(int life, float expiration) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void increaseLife(int life) {
        this.life += life;
    }
    
    public void decreaseLife(int life) {
        this.life -= life;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }
}
