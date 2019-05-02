/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import data.GameData;
import data.World;
import enums.State;

/**
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author ahmadhamid
 */
public class Game implements ApplicationListener {
    
    private GameStateManager gameStateManager;

    private final GameData gameData = new GameData();
//    private List<IPluginService> entityPlugins = new ArrayList<>();
    private final World world = new World();

    @Override
    public void create() {
        gameStateManager = new GameStateManager(gameData, world);
        gameStateManager.setState(State.SPLASHSTATE); 
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

}
