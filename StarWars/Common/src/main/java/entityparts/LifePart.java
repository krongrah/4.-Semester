/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityparts;

import common.Entity;
import data.GameData;

/**
 * Class is used for Entities to be able To have a life property
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public class LifePart implements EntityPart {

    private int life;
    private boolean isHit = false;

    /**
     * Is the constructor for the LifePart
     *
     * @param life Sets the amount of life
     */
    public LifePart(int life) {
        this.life = life;
    }

    /**
     * Returns the amount of life
     *
     * @return integer life
     */
    public int getLife() {
        return life;
    }

    /**
     * Sets the life to the given value
     *
     * @param life
     */
    public void setLife(int life) {
        this.life = life;
    }

    public void increaseLife(int life) {
        this.life += life;
    }
    
    public void decreaseLife(int life) {
        this.life -= life;
    }

    /**
     * Returns the boolean whether the Entity holding the LifePart has been hit
     *
     * @return isHit
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * Sets the isHit to the given value
     *
     * @param isHit
     */
    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    /**
     * Processes the LifePart eg. if the boolean isHit is true, the process
     * might react to this
     *
     * @param gameData Is the data used to process
     * @param entity Is the original Entity holding the LifePart
     */
    @Override
    public void process(GameData gameData, Entity entity) {
        
    }
}
