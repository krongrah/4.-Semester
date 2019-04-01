package entityparts;

import common.Entity;
import data.GameData;
import enums.CollisionTypes;
import enums.Directions;
import sprites.Sprites;

/**
 *
 * @author Krongrah
 */
public class PropertiesPart implements EntityPart {

    private float height;
    private float width;
    private CollisionTypes colType;

    public PropertiesPart(float width,float height) {
        this.height = height;
        this.width = width;
    }

    public void setCollisionType(CollisionTypes colType) {
        this.colType = colType;
    }

    public CollisionTypes getCollisionType() {
        return colType;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }

}
