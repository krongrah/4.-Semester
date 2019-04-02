package entityparts;

import common.Entity;
import data.GameData;
import sprites.Sprites;

/**
 *
 * @author Krongrah
 */
public class PropertiesPart implements EntityPart {

    private float height;
    private float width;
    private boolean solid;
    private boolean obstacle;

    public PropertiesPart(float width, float height, boolean solid) {
        this.height = height;
        this.width = width;
        this.solid = solid;
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

    @Override
    public void process(GameData gameData, Entity entity) {

    }

}
