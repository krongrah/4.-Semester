/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityparts;

import common.Entity;
import data.GameData;
import java.io.File;

/**
 *
 * @author Naik
 */
public class SoundPart implements EntityPart {
    
    private File soundFile;
    private boolean shouldPlay = false;
    private boolean hasFrameElapsed = false;
    
    public SoundPart(File soundFile){
        this.soundFile = soundFile;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if(shouldPlay){
        if(hasFrameElapsed){
            hasFrameElapsed = false;
            shouldPlay = false;    
        } else{
            hasFrameElapsed = true;
        }
        }
    }

    public boolean shouldPlay() {
        return shouldPlay;
        
    }
    

    
    public void startPlaying(){
        System.out.println("sound activated");
        shouldPlay = true;
    }
    
    public File getSound(){
        return soundFile;
    }
}
