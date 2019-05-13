/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.weapon;

import data.GameData;
import data.World;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;
import Animation.Animation;
import java.io.IOException;
import org.openide.util.Exceptions;

/**
 *
 * @author Sebas
 */
@ServiceProvider(service = IPluginService.class)

public class WeaponPlugin implements IPluginService {

    List<Animation> list = new ArrayList();
    
    @Override
    public void start(GameData gameData, World world) {
        
        try {
            list.add(new Animation(WeaponPlugin.class.getResource("sprites/bullet0.png").getPath().substring(5), "bullet", 1));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    @Override
    public List<Animation> getAnimation() {
        
        return list;
    }

}
