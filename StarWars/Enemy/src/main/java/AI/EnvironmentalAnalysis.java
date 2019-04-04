/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import services.IProcessor;

/**
 *
 * @author Sebas
 */


@ServiceProvider(service = IProcessor.class)

public class EnvironmentalAnalysis implements IProcessor {

    @Override
    public void process(GameData gameData, World world) {
        
    }
    
}
