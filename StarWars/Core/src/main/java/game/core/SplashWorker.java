/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import java.awt.SplashScreen;


/**
 *
 * @author andreasmolgaard-andersen
 */
public class SplashWorker implements ISplashWorker {

    @Override
    public void closeSplashScreen() {
        SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash != null) {
            splash.close();
        }
    }

    
    
}
