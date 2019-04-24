/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.Entity;
import data.GameData;
import data.World;
import entityparts.SelfDestructPart;
import org.openide.util.lookup.ServiceProvider;


@ServiceProvider(service = IPostProcessor.class)
/**
 *
 * @author Naik
 */
public class Exterminator implements IPostProcessor {

    @Override
    public void process(GameData gameData, World world) {
        for(Entity ent : world.getEntities()){
            SelfDestructPart spart = ent.getPart(SelfDestructPart.class);
            if(spart !=null){
                if(spart.shouldDestroy()){
                    world.removeEntity(ent);
                }
            }
        }
    }
    
}
