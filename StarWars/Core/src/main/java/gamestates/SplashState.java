/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import data.GameData;
import static data.GameKeys.ENTER;
import data.World;
import enums.State;
import game.core.GameInputProcessor;
import game.core.GameStateManager;
import game.renderer.Renderer;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class SplashState extends PlayState {
    
    private Renderer renderer;
    private GameData gameData;
    private World world;
    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    
    public SplashState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }
    
    @Override
    public void init(GameData gameData, World world) {
        
        this.gameData = gameData;
        this.world = world;
        renderer = new Renderer(world);
        
        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.update();
        
        sr = new ShapeRenderer();
        
        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );
        
    }

    @Override
    public void update() {
        if (gameData.getKeys().isDown(ENTER)) {
            this.gameStateManager.setState(State.PLAYSTATE);
        }
        
    }

    @Override
    public void draw() {
        this.render();
    }
    
    private void render() {
        renderer.render(world, gameData, State.SPLASHSTATE);
    }

    @Override
    public void handleInput() {
        gameData.getKeys().update();
    }

    @Override
    public void dispose() {
        
    }
    
}
