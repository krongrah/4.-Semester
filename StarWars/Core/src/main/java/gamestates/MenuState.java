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
public class MenuState extends GameState {

    private GameData gameData;
    private World world;
    
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }
    
    @Override
    public void init(GameData gameData, World world) {
        this.gameData = gameData;
        this.world = world;
        
        
        
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleInput() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
