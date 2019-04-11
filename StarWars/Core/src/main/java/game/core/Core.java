/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Core extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;
    private SplashWorker splashWorker;    

    @Override
    public void create() {
        splashWorker.closeSplashScreen();
        batch = new SpriteBatch();
        img = new Texture(Gdx.files.absolute("/Users/andreasmolgaard-andersen/Documents/GitHub/4.-Semester/StarWars/Core/src/main/resources/images/splash.png"));
//        img = new Texture("/images/splash.png");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0, 1080, 720);
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    public SplashWorker getSplashWorker() {
        return splashWorker;
    }

    public void setSplashWorker(SplashWorker splashWorker) {
        this.splashWorker = splashWorker;
    }
}
