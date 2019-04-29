/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.renderer;

import assetmanagement.AssetManagerClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import data.GameData;
import data.World;
import entityparts.AnimationPart;

/**
 *
 * @author andreasmolgaard-andersen
 */
public final class SplashScreenDrawer {

    private final SpriteBatch batch;
    AssetManagerClass am;
    private final AnimationPart ap;

    public SplashScreenDrawer(String currentAnimation, int framesInCurrentAnimation) {
        ap = new AnimationPart(currentAnimation, framesInCurrentAnimation, getPath());
        batch = new SpriteBatch();
        am = new AssetManagerClass();
        this.Load();
    }

    public void drawSplashScreen(World world, GameData gameData, Matrix4 matrix) {
        this.process(gameData);
        batch.setProjectionMatrix(matrix);
        batch.begin();

        Sprite sprite = am.getSprite(ap.getCurrentAnimation(), getPath());
        sprite.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest); //This renders pixelart sharper than linear rendering
        sprite.setPosition(0 - gameData.getDisplayWidth() / 2, 0 - gameData.getDisplayHeight() / 2);

        sprite.draw(batch);
        batch.end();

    }

    private String getPath() {
        return SplashScreenDrawer.class.getResource("/sprites/SplashScreen.txt").getPath();
    }

    private void Load() {
        am.Load(getPath());
    }

    private void process(GameData gameData) {
        
        ap.process(gameData, null);

    }


}
