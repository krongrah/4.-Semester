/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.renderer;

import assetmanagement.AssetManagerClass;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import common.Entity;
import data.GameData;
import data.World;
import entityparts.AnimationPart;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import enums.Directions;

/**
 *
 * @author Krongrah
 */
public class UnitDrawBoard {

    private SpriteBatch batch;
    AssetManagerClass am;

    public UnitDrawBoard() {
        batch = new SpriteBatch();
        am = new AssetManagerClass();
    }

    public void draw(World world, GameData gameData, Matrix4 matrix) {
        batch.setProjectionMatrix(matrix);
        batch.begin();
        for (Entity entity : world.getEntities()) {
            PropertiesPart prop = entity.getPart(PropertiesPart.class);
            if (prop!=null && !prop.isObstacle()) {
                PositionPart pos = entity.getPart(PositionPart.class);
                AnimationPart ani = entity.getPart(AnimationPart.class);
                Sprite sprite = am.getSprite(ani.getCurrentAnimation(), ani.getSpriteSheetPath());
                sprite.setPosition(pos.getX() - sprite.getWidth() / 2, pos.getY() - sprite.getHeight() / 2);
                if (pos.getDirection() == Directions.LEFT) {
                    sprite.flip(true, false);
                    sprite.setX(sprite.getX()-sprite.getWidth()/2);
                }

                //sprite.scale(camera.zoom);
                sprite.draw(batch);
            }

        }
        batch.end();
    }

    public void Load(String path) {
        am.Load(path);
    }

}
