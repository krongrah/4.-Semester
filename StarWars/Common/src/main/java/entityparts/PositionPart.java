package entityparts;

import common.Entity;
import data.GameData;
import enums.Directions;

/**
 * Class is used for Entities To be able to hold a position
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public class PositionPart implements EntityPart {

    private float x;
    private float y;
    private Directions direction;

    /**
     * Constructor for PositionPart
     *
     * @param x Is the x value of the PositionPart
     * @param y Is the y value of the PositionPart
     */
    public PositionPart(float x, float y) {
        this.x = x;
        this.y = y;
        direction = Directions.RIGHT;
    }

    /**
     *
     * @return The x value of the position part
     */
    public float getX() {
        return x;
    }

    /**
     *
     * @return The y value of the position part
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the x value of the position part
     *
     * @param newX Is the new x value to be set
     */
    public void setX(float newX) {
        this.x = newX;
    }

    /**
     * Sets the y value of the position part
     *
     * @param newY Is the new y value to be set
     */
    public void setY(float newY) {
        this.y = newY;
    }

    /**
     * Sets both the x and y value of the position part
     *
     * @param newX Is the x value to be set
     * @param newY Is the y value to be set
     */
    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setDirection(Directions dir) {
        this.direction = dir;
    }
    
    public Directions getDirection() {
        return direction;
    }
    
    /**
     * Processes the position part eg. updates the position
     *
     * @param gameData Is used to get data which is manipulates
     * @param entity Is the Entity where the position part comes from
     */
    @Override
    public void process(GameData gameData, Entity entity) {
        
        
    }
}
