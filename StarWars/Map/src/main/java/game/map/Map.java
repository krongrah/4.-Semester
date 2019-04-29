/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import common.AISpawnPoint;
import common.Entity;
import data.World;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import enums.AITypes;
import enums.CollisionTypes;

/**
 *
 * @author Krongrah
 */
public class Map {

    private TmxMapLoader mapLoader;
    private TiledMap currentMap;
    private static Map map;
    private World world;

    private Map(World world) {
        this.world = world;
        mapLoader = new TmxMapLoader();
        loadNewMap("untitled");
    }

    public static Map getInstance(World world) {
        if (map == null) {
            map = new Map(world);
        }
        return map;
    }

    public TiledMap getMap() {
        return currentMap;
    }

    public void loadNewMap(String name) {
        currentMap = mapLoader.load(Map.class.getResource("/maps/Tatooine/" + name + ".tmx").getPath());
        for (MapObject obj : currentMap.getLayers().get("Object Layer 1").getObjects()) {

            if (obj instanceof RectangleMapObject) {
                RectangleMapObject recObj = (RectangleMapObject) obj;
                if (recObj.getName().equals("Ground")) {
                    //Ground object
                    Entity ground = new Entity();
                    PositionPart pos = new PositionPart(recObj.getRectangle().x + recObj.getRectangle().width / 2, recObj.getRectangle().y + recObj.getRectangle().height / 2);
                    PropertiesPart prop = new PropertiesPart(recObj.getRectangle().width / 2, recObj.getRectangle().height / 2, CollisionTypes.SOLIDOBJECT, true);
                    ground.add(prop);
                    ground.add(pos);
                    world.addEntity(ground);

                }
                if (recObj.getName().equals("ShooterEnemySpawn")) {
                    //Spawn enemy here:
                    
                    PropertiesPart prop = new PropertiesPart(recObj.getRectangle().width, recObj.getRectangle().height, CollisionTypes.SOLIDOBJECT, false);
                    PositionPart pos = new PositionPart(recObj.getRectangle().x + recObj.getRectangle().width / 2, recObj.getRectangle().y + recObj.getRectangle().height / 2);
                    AISpawnPoint point = new AISpawnPoint(pos, prop, AITypes.SHOOTER);
                    world.addEnemySpawn(point);

                }
                if (recObj.getName().equals("MeleeEnemySpawn")) {
                    //Spawn enemy here:
                    PropertiesPart prop = new PropertiesPart(recObj.getRectangle().width, recObj.getRectangle().height, CollisionTypes.SOLIDOBJECT, false);
                    PositionPart pos = new PositionPart(recObj.getRectangle().x + recObj.getRectangle().width / 2, recObj.getRectangle().y + recObj.getRectangle().height / 2);
                    AISpawnPoint point = new AISpawnPoint(pos, prop, AITypes.MELEE);
                    world.addEnemySpawn(point);
                }
            }
        }
    }
}
