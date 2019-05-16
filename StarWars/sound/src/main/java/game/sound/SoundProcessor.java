/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.sound;

import data.GameData;
import data.World;
import java.io.File;
import java.util.HashMap;
import org.openide.util.lookup.ServiceProvider;
import services.IProcessor;

/**
 *
 * @author Naik
 */
@ServiceProvider(service = IProcessor.class)

public class SoundProcessor implements IProcessor {
    private HashMap<File, String> soundMap = new HashMap();
    

    @Override
    public void process(GameData gameData, World world) {
        for ( File soundFile : world.getSoundList()){
            if(soundMap.containsKey(soundFile)){
                soundCommands.play(soundMap.get(soundFile));
                              
            }else{
                soundMap.put(soundFile, soundFile.getName());
                soundCommands.load(soundFile.getPath(), soundFile.getName());
                soundCommands.play(soundMap.get(soundFile));
            }
        }
        world.clearSoundList();
        
                
    }
    
}
