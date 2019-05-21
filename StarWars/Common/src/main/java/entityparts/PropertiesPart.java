package entityparts;

import common.Entity;
import data.GameData;
import enums.CollisionTypes;

/**
 *
 * @author Krongrah
 */
public class PropertiesPart implements EntityPart {

    private float height;
    private float width;
    private CollisionTypes colType;
    private boolean obstacle;

    public PropertiesPart(float height, float width, CollisionTypes colType, boolean obstacle) {
        this.height = height;
        this.width = width;
        this.colType = colType;
        this.obstacle = obstacle;
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

    public boolean isObstacle() {
        return obstacle;
    }

}
