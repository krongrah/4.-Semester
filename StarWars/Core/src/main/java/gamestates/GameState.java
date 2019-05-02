/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestates;

import data.GameData;
import data.World;
import game.core.GameStateManager;

/**
 *
 * @author andreasmolgaard-andersen
 */
public abstract class GameState {
    
    protected GameStateManager gameStateManager;
    
    protected GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }
    
    public abstract void init(GameData gameData, World world);
    public abstract void update();
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
    
}
