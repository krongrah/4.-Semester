/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetmanagement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sprites.Sprites;

/**
 *
 * @author Krongrah
 */
public class Assets {

    private AssetManager manager;

    public Assets() {
        manager = new AssetManager();
        loadAsset();
    }

//    public Sprite getSprite(Sprites sprite) {
//        if (!manager.isLoaded("/resources/sprites/luke.png")) {
//            loadAsset();
//        }
//        
//        return new Sprite((Texture)manager.get(("/resources/sprites/luke.png")));
//    }

    public Sprite getSprite(Sprites sprite) {
    return new Sprite(new Texture(Gdx.files.internal("resources/sprites/luke.png")));
    }
    
    private void loadAsset(){
    manager.load(("/resources/sprites/luke.png"), Texture.class);
            while (manager.update()) {
            }
    }
    
}
