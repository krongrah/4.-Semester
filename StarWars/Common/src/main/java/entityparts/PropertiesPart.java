package entityparts;

import common.Entity;
import data.GameData;
import enums.CollisionTypes;
import sprites.Sprites;

/**
 *
 * @author Krongrah
 */
public class PropertiesPart implements EntityPart {

    private float height;
    private float width;
    private Sprites sprite;
    private boolean solid;
    private boolean obstacle;
    private CollisionTypes colType;

    public PropertiesPart(float height, float width, Sprites sprite, boolean solid) {
        this.height = height;
        this.width = width;
        this.sprite = sprite;
        this.solid = solid;
    }

    public void setCollisionType(CollisionTypes colType) {
        this.colType = colType;
    }

    public CollisionTypes getCollisionType() {
        return colType;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public float getHeight() {
        return height;
    }

    public boolean isSolid() {
        return solid;
    }

    public float getWidth() {
        return width;
    }

    public Sprites getSprite() {
        return sprite;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }

}
