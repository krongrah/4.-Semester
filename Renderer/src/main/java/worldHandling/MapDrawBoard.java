/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldHandling;

import static Constants.Constants.HEIGHT;
import static Constants.Constants.WIDTH;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import data.GameData;
import game.tiledmap.Map;

/**
 *
 * @author Krongrah
 */
public class MapDrawBoard {

    private MapRenderer renderer;

    public MapDrawBoard() {
        renderer = new OrthogonalTiledMapRenderer(Map.getInstance().getMap());

    }

    public void render(OrthographicCamera cam) {

    }

    public void loadMap() {
        renderer = new OrthogonalTiledMapRenderer(Map.getInstance().getMap());
    }

    public void render(GameData gameData, Matrix4 projectionMatrix) {
        renderer.setView(projectionMatrix, 
                gameData.getFocusX() - WIDTH / 2,
                gameData.getFocusY() - HEIGHT / 2, 
                gameData.getFocusX() + WIDTH / 2,
                gameData.getFocusY() + HEIGHT / 2);
        renderer.render();
    }

}
