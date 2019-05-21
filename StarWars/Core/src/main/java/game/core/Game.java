/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import common.Entity;
import data.GameData;
import data.World;
import entityparts.EntityPart;
import game.renderer.Renderer;
import game.sound.soundCommands;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.LookupEvent;
import services.IProcessor;
import services.IPluginService;
import services.IPostProcessor;
import services.IRenderer;
import enums.State;

/**
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author ahmadhamid
 */
public class Game implements ApplicationListener {
    
    
    
    private final Lookup lookup = Lookup.getDefault();
    private Lookup.Result<IPluginService> result;
    private Lookup.Result<IPostProcessor> postProcessorResults;
    private List<IPluginService> gamePlugins = new CopyOnWriteArrayList<>();
    private List<IPostProcessor> postProcessors = new ArrayList<>();
private List<IProcessor> entityProcessors = new ArrayList<>();
    
    private GameStateManager gameStateManager;

    private final GameData gameData = new GameData();
//    private List<IPluginService> entityPlugins = new ArrayList<>();

    
    private HashMap<File, String> soundMap = new HashMap();
    private final World world = new World();


    @Override
    public void create() {
        gameStateManager = new GameStateManager(gameData, world);
//        gameStateManager.setState(State.SPLASHSTATE); 
        gameStateManager.setState(State.PLAYSTATE); 

    }

    @Override
    public void render() {
        // clear screen to black
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();
        
        handleInput();

//        gameData.getKeys().update();
//        renderer.render(world, gameData);

    }

    private void update() {
        // Update
        for (IProcessor entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
//        Goes through the list of sound files and plays them

//        for ( File soundFile : world.getSoundList()){
//            if(soundMap.containsKey(soundFile)){
//                soundCommands.play(soundMap.get(soundFile));
//                
//            }else{
//                soundMap.put(soundFile, soundFile.getName());
//                soundCommands.load(soundFile.getPath(), soundFile.getName());
//                soundCommands.play(soundMap.get(soundFile));
//            }
//        }
//        world.clearSoundList();
        
        // Calls entityparts process. This should always be before post processing
        for (Entity ent : world.getEntities()){
            for (EntityPart ep : ent.getAllParts()){
                ep.process(gameData, ent);
            }
                    
        }
                
        
        
        for (IPostProcessor postProcessor : postProcessors) {
            postProcessor.process(gameData, world);
        }
        
        world.performDeletion();
        gameStateManager.update();
    }

    private void draw() {
        gameStateManager.draw();
    }

    private void handleInput() {
        gameStateManager.handleInput();
    }
    
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        gameStateManager.dispose();
    }

    private Collection<? extends IProcessor> getEntityProcessingServices() {
        return lookup.lookupAll(IProcessor.class);
        //return SPILocator.locateAll(IProcessor.class);
    }

    private Collection<? extends IPostProcessor> getEntityPostProcessingServices() {
        return lookup.lookupAll(IPostProcessor.class);
        
//        return SPILocator.locateAll(IPostProcessor.class);
    }
    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {

            Collection<? extends IPluginService> updated = result.allInstances();

            for (IPluginService us : updated) {
                // Newly installed modules
                if (!gamePlugins.contains(us)) {
                    us.start(gameData, world);
                    gamePlugins.add(us);
                }
            }

            // Stop and remove module
            for (IPluginService gs : gamePlugins) {
                if (!updated.contains(gs)) {
                    gs.stop(gameData, world);
                    gamePlugins.remove(gs);
                }
            }
        }

    };

}
