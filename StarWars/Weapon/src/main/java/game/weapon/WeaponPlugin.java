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
import sprites.Animation;

/**
 *
 * @author Sebas
 */
@ServiceProvider(service = IPluginService.class)

public class WeaponPlugin implements IPluginService {

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    @Override
    public List<Animation> getAnimation() {
        List<Animation> list = new ArrayList();
        list.add(new Animation(WeaponPlugin.class.getResource("/sprites/").getPath(), "bullet", 0));
        return list;
    }

}
