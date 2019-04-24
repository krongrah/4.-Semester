/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.sound;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.SoundPart;
import org.openide.util.lookup.ServiceProvider;
import services.IPluginService;
import services.IPostProcessor;


@ServiceProvider(service = IPostProcessor.class)
/**
 *
 * @author Naik
 */
public class SoundSystem implements IPostProcessor {

    @Override
    public void process(GameData gd, World world) {
        
        for (Entity e : world.getEntities()){
            SoundPart sp = e.getPart(SoundPart.class);
            if(sp != null){
                
            if (sp.shouldPlay()){
                System.out.println("doyouexist");
                world.addSound(sp.getSound());
            }
            }
            
        }
    }
    
}
