/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.renderer;

import assetmanagement.AssetManagerClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import common.Entity;
import data.GameData;
import data.World;
import entityparts.AnimationPart;
import entityparts.PositionPart;
import game.map.Map;
import services.IRenderer;

/**
 *
 * @author ahmadhamid
 */
public class Renderer implements IRenderer {

    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Color backgroundColor;
    private SpriteBatch batch;
    private AssetManagerClass am;
    private Sprite sprite;

    public Renderer() {
        am = new AssetManagerClass();
        batch = new SpriteBatch();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        Gdx.graphics.setVSync(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(Map.getInstance().getMap()); //must get the map from the map component here
    }

    @Override
    public void render(World world, GameData gameData) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a); //Gets the RGBA values of the backgound Color
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //tiledMapRenderer.setView(camera);
        
        tiledMapRenderer.setView(camera.combined, gameData.getFocusX() - gameData.getDisplayWidth() / 2, gameData.getFocusY() - gameData.getDisplayHeight() / 2, gameData.getDisplayWidth(), gameData.getDisplayHeight());
        
        tiledMapRenderer.render();

        camera.position.set(gameData.getFocusX(), gameData.getFocusY(), 0); //X, Y, Z coordinates
        
        camera.update();
        
        for (Entity entity : world.getEntities()) {
            draw(entity);
        }

    }

    @Override
    public void setBackgroudColor(float r, float g, float b, float a) {
        this.backgroundColor = new Color(r, g, b, a);
    }

    private void draw(Entity entity) {
        AnimationPart part = entity.getPart(AnimationPart.class);
        PositionPart posPart = entity.getPart((PositionPart.class));
        batch.begin();

        sprite = am.getSprite(part.getCurrentAnimation(), part.getSpriteSheetPath());
        
        sprite.setPosition((camera.viewportWidth / 2) - (sprite.getWidth() / 2),(camera.viewportHeight / 2) - (sprite.getHeight() / 2));      
        
        sprite.draw(batch);
        
        batch.end();
    }

    //@Override
    public void loadTexture(String path) {
        am.Load(path);
    }

}