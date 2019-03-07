/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import data.GameData;
import data.GameKeys;

/**
 *
 * @author ahmadhamid
 */
public class GameInputProcessor extends InputAdapter {

    private final GameData gameData;

    public GameInputProcessor(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public boolean keyDown(int k) {
        if (k == Input.Keys.W || k == Input.Keys.UP) {
            gameData.getKeys().setKey(GameKeys.W, true);
        }
        if (k == Input.Keys.A || k == Input.Keys.LEFT) {
            gameData.getKeys().setKey(GameKeys.A, true);
        }
        if (k == Input.Keys.S || k == Input.Keys.DOWN) {
            gameData.getKeys().setKey(GameKeys.S, true);
        }
        if (k == Input.Keys.D || k == Input.Keys.RIGHT) {
            gameData.getKeys().setKey(GameKeys.D, true);
        }
        if (k == Input.Keys.Q) {
            gameData.getKeys().setKey(GameKeys.Q, true);
        }
        if (k == Input.Keys.E) {
            gameData.getKeys().setKey(GameKeys.E, true);
        }
        if (k == Input.Keys.ENTER) {
            gameData.getKeys().setKey(GameKeys.ENTER, true);
        }
        if (k == Input.Keys.ESCAPE) {
            gameData.getKeys().setKey(GameKeys.ESCAPE, true);
        }
        if (k == Input.Keys.SPACE) {
            gameData.getKeys().setKey(GameKeys.SPACE, true);
        }
        if (k == Input.Keys.SHIFT_LEFT || k == Input.Keys.SHIFT_RIGHT) {
            gameData.getKeys().setKey(GameKeys.SHIFT, true);
        }
        return true;
    }

    public boolean keyUp(int k) {
        if (k == Input.Keys.UP) {
            gameData.getKeys().setKey(GameKeys.W, false);
        }
        if (k == Input.Keys.LEFT) {
            gameData.getKeys().setKey(GameKeys.A, false);
        }
        if (k == Input.Keys.DOWN) {
            gameData.getKeys().setKey(GameKeys.S, false);
        }
        if (k == Input.Keys.RIGHT) {
            gameData.getKeys().setKey(GameKeys.D, false);
        }
        if (k == Input.Keys.ENTER) {
            gameData.getKeys().setKey(GameKeys.ENTER, false);
        }
        if (k == Input.Keys.ESCAPE) {
            gameData.getKeys().setKey(GameKeys.ESCAPE, false);
        }
        if (k == Input.Keys.SPACE) {
            gameData.getKeys().setKey(GameKeys.SPACE, false);
        }
        if (k == Input.Keys.SHIFT_LEFT || k == Input.Keys.SHIFT_RIGHT) {
            gameData.getKeys().setKey(GameKeys.SHIFT, false);
        }
        return true;
    }
}
