/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.enemy;

import data.GameData;
import data.World;
import entityparts.AnimationPart;
import entityparts.LifePart;
import entityparts.MovingPart;
import entityparts.PositionPart;
import entityparts.PropertiesPart;
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;
import sprites.Sprites;

@ServiceProvider(service = IPluginService.class)

/**
 *
 * @author andreasmolgaard-andersen
 */
public class EnemyPlugin implements IPluginService {
    
    private Enemy enemy;
    
    @Override
    public void start(GameData gameData, World world) {
        enemy = new Enemy();
        
        enemy.add(new PropertiesPart(50, 100, Sprites.ATST, true));
        enemy.add(new LifePart(3));
        enemy.add(new PositionPart(gameData.getDisplayWidth(), 0));
        enemy.add(new MovingPart(10, 100, 100));
        enemy.add(new AnimationPart("Blaster", 0, getPath()));
        
        //world.addEntity(enemy);
        
    }
    
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }

    @Override
    public String getPath() {
        return "";
    }
    
}
