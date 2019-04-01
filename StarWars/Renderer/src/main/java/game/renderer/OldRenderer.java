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
import entityparts.PropertiesPart;
import enums.Directions;
import game.map.Map;
import services.IRenderer;

public class OldRenderer {

    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Color backgroundColor;
    private SpriteBatch batch;
    private AssetManagerClass am;
    private Sprite sprite;

    public OldRenderer(World world) {
        am = new AssetManagerClass();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        Gdx.graphics.setVSync(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);

        //camera.viewportHeight = h;
        //camera.viewportWidth = w;
        camera.viewportHeight = h / 2;
        camera.viewportWidth = w / 2;
        camera.update();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(Map.getInstance(world).getMap()); //must get the map from the map component here
    }

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
            PropertiesPart prop = entity.getPart(PropertiesPart.class);
            //if (!prop.isObstacle()) {
                //draw(entity);
            //}
        }
    }

//    public void setBackgroundColor(float r, float g, float b, float a) {
//        this.backgroundColor = new Color(r / 255, g / 255, b / 255, a / 255);
//    }
    private void draw(Entity entity) {
        batch = new SpriteBatch();

        AnimationPart part = entity.getPart(AnimationPart.class);
        PositionPart pp = entity.getPart(PositionPart.class);
        batch.begin();

        sprite = am.getSprite(part.getCurrentAnimation(), part.getSpriteSheetPath());

        sprite.setPosition((pp.getX()) - (sprite.getWidth() / 2), (pp.getY()) - (sprite.getHeight() / 2));

        if (pp.getDirection() == Directions.LEFT) {
            sprite.flip(true, false);
        }

        sprite.scale(camera.zoom);
        sprite.draw(batch);

        batch.end();
    }

//    //@Override
//    public void loadTexture(String path) {
//        am.Load(path);
//    }
}
