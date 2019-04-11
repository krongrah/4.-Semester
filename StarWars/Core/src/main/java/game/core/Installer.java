/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

public class Installer extends ModuleInstall {

    private static Game g;
    
    LwjglApplication lwjglApplication;
    
    @Override
    public void restored() {
        
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        Core core = new Core();
        core.setSplashWorker(new SplashWorker());
        config.title = "Splash Screen";
        config.width = 1080;
        config.height = 720;
        lwjglApplication = new LwjglApplication(core, config);
        
//        lwjglApplication.stop();
//        lwjglApplication.exit();
        
        
        this.startApplication();
        
        
    }
    
    private void startApplication() {
        
        g = new Game();
        
        LwjglApplicationConfiguration cfg
                = new LwjglApplicationConfiguration();
        cfg.title = "Star Wars - Fall of the Empire";
        cfg.width = 720;
        cfg.height = 480;
        cfg.useGL30 = false;
        cfg.resizable = false;
        cfg.fullscreen = false;
        cfg.x = 1;
        cfg.y = 1;

        //new LwjglApplication(new Game(), cfg);
        lwjglApplication = new LwjglApplication(g, cfg);     
    }
}
