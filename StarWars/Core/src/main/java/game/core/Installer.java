/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    private static Game g;
    
    @Override
    public void restored() {
        
        g = new Game();
        
        LwjglApplicationConfiguration cfg
                = new LwjglApplicationConfiguration();
        cfg.title = "Star Wars - Fall of the Empire";
        cfg.width = 720;
        cfg.height = 480;
        cfg.useGL30 = false;
        cfg.resizable = false;

        //new LwjglApplication(new Game(), cfg);
        LwjglApplication lwjglApplication = new LwjglApplication(g, cfg);
    }

}
