/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer;

import SpriteHandling.AnimationTracker;
import SpriteHandling.SpriteFinder;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import data.Entity;
import data.GameData;
import interfaces.IWorld;

/**
 *
 * @author Krongrah
 */
public class UnitDrawBoard {

    private SpriteBatch batch;
    private SpriteFinder spriteFinder;
    private AnimationTracker animationTracker;

    public UnitDrawBoard() {
        batch = new SpriteBatch();
        animationTracker = new AnimationTracker();
        spriteFinder = new SpriteFinder(animationTracker);
    }

    public void draw(IWorld world, GameData gameData, Matrix4 matrix) {
        batch.setProjectionMatrix(matrix);
        batch.begin();
        
        animationTracker.update(gameData);
        for (Entity entity : world.getEntities()) {
            spriteFinder.getSprite(entity, gameData.getFocusAngle()).draw(batch);
        }
        batch.end();
    }

}
