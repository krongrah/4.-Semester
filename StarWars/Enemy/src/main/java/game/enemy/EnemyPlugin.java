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
import enums.CollisionTypes;
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
        MovingPart mov=new MovingPart(10, 175, 250);
        enemy.add(new PropertiesPart(32, 32, CollisionTypes.SOLIDOBJECT,false));
        enemy.add(new LifePart(3));
        enemy.add(new PositionPart(24*32, (39*32)+16));
        enemy.add(mov);
        enemy.add(new AnimationPart("Lukeidle", 5, getPath()));
        //mov.setLeft(true);
        
        world.addEntity(enemy);
        
        
        enemy = new Enemy();
        
        mov=new MovingPart(10, 100, 100);
        
        enemy.add(new PropertiesPart(32, 32, CollisionTypes.SOLIDOBJECT,false));
        enemy.add(new LifePart(3));
        enemy.add(new PositionPart(10*32, (39*32)+16));
        enemy.add(mov);
        enemy.add(new AnimationPart("Lukeidle", 5, getPath()));
        //mov.setRight(true);
        world.addEntity(enemy);
        
    }
    
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }

    @Override
    public String getPath() {
        return EnemyPlugin.class.getResource("/sprites/Luke.txt").getPath();
    }
    
}
