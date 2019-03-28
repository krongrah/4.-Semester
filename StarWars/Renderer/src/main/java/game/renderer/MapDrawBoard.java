/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import data.GameData;
import data.World;
import game.map.Map;

/**
 *
 * @author Krongrah
 */
public class MapDrawBoard {

    private MapRenderer renderer;
    private World world;

    public MapDrawBoard(World world) {
        this.world = world;
        renderer = new OrthogonalTiledMapRenderer(Map.getInstance(world).getMap());

    }

    public void render(OrthographicCamera cam) {

    }

    public void loadMap() {
        renderer = new OrthogonalTiledMapRenderer(Map.getInstance(world).getMap());
    }

    public void render(GameData gameData, Matrix4 projectionMatrix) {
        for (MapObject object : Map.getInstance(world).getMap().getLayers().get(1).getObjects()) {
            System.out.println(object.getName());
        }
        renderer.setView(projectionMatrix,
                gameData.getFocusX() - gameData.getDisplayWidth() / 2,
                gameData.getFocusY() - gameData.getDisplayHeight() / 2,
                gameData.getDisplayWidth(),
                gameData.getDisplayHeight()
        );
        renderer.render();
    }

}
