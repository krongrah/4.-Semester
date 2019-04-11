/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import data.GameData;
import data.World;
import gamestates.GameState;
import gamestates.MenuState;
import gamestates.PlayState;
import gamestates.State;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class GameStateManager {
    
    private GameState gameState;
    
    private final GameData gameData;
    private World world;
    
    public GameStateManager(GameData gameData, World world) {
        this.gameData = gameData;
        this.world = world;
    }
    
    public void setState(State gameState) {
        
        switch (gameState) {
            case PLAYSTATE:
                this.gameState = new PlayState(this);
                this.init();
                break;
            case MENUSTATE:
                this.gameState = new MenuState(this);
                this.init();
                break;
//            case: gameState.SPLASHSTATE:
//                this.gameState = new SplastState(this);
//                break;

        }
        
    }
    
    public void init() {
        gameState.init(gameData, world);
    }
    
    public void update() {
        gameState.update();
    }
    
    public void draw() {
        gameState.draw();
    }
    
    public void handleInput() {
        gameState.handleInput();
    }
    
    public void dispose() {
        gameState.dispose();
    }
    
    
    
}
