/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 *
 * @author Krongrah
 */
public class Map {

    private TmxMapLoader mapLoader;
    private TiledMap currentMap;
    private static Map map;

    private Map() {
        mapLoader = new TmxMapLoader();
        loadNewMap("gameart2d-desert");
    }

    public static Map getInstance() {
        if (map == null) {
            map = new Map();
        }
        return map;
    }

    public TiledMap getMap() {
        return currentMap;
    }

    public void loadNewMap(String name) {
        //Map.class.getResource("/Tatooine/" + name + ".tmx").getPath();
        currentMap = mapLoader.load(Map.class.getResource("/maps/test/" + name + ".tmx").getPath());
//        currentMap = mapLoader.load(name + ".tmx");
    }
}
