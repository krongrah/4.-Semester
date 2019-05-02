/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.weapon;

import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;

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
    public String getPath() {
        return WeaponPlugin.class.getResource("/sprites/bullet.txt").getPath();
    }

}
