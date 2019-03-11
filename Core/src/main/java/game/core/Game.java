/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import common.Entity;
import data.GameData;
import data.World;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import renderer.Renderer;
import services.IProcessor;
import services.IPluginService;
import services.IRenderer;
import util.SPILocator;

/**
 *
 * @author ahmadhamid
 */
public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private IRenderer renderer;

    private final GameData gameData = new GameData();
    private List<IProcessor> entityProcessors = new ArrayList<>();
//    private List<IPluginService> entityPlugins = new ArrayList<>();
    private World world = new World();

    @Override
    public void create() {

        renderer = new Renderer();
        
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

//        IGamePluginService playerPlugin = new PlayerPlugin();
//        IEntityProcessingService playerProcess = new PlayerControlSystem();
//        entityPlugins.add(playerPlugin);
//        entityProcessors.add(playerProcess);
        // Lookup all Game Plugins using ServiceLoader
        
        for (IPluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
        renderer.setBackgroudColor(1, 1, 1, 1);
        renderer.render(world, gameData);

        
    }

    private void update() {
        // Update
        for (IProcessor entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {

        }
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
    }
    
    private Collection<? extends IPluginService> getPluginServices() {
        return SPILocator.locateAll(IPluginService.class);
    }

    private Collection<? extends IProcessor> getEntityProcessingServices() {
        return SPILocator.locateAll(IProcessor.class);
    }
    
//    private Collection<? extends IPostProcessor> getEntityPostProcessingServices() {
//        return SPILocator.locateAll(IPostProcessor.class);
//    }
    
}
