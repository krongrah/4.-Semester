/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import data.GameData;
import data.World;
import map.Map;
import services.IRenderer;

/**
 *
 * @author ahmadhamid
 */
public class Renderer implements IRenderer{

    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Color backgroundColor;

    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        //tiledMap = new TmxMapLoader().load("gameart2d-desert.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(Map.getInstance().getMap()); //must get the map from the map component here
    }

    @Override
    public void render(World world, GameData gameData) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a); //Gets the RGBA values of the backgound Color
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        camera.position.set(gameData.getFocusX(), gameData.getFocusY(), 0); //X, Y, Z coordinates
    }

    @Override
    public void setBackgroudColor(float r, float g, float b, float a) {
        this.backgroundColor = new Color(r,g,b,a);
    }
}
