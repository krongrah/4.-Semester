/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import common.Entity;
import entityparts.PositionPart;

/**
 *
 * @author ahmadhamid
 */
public class GameS extends ApplicationAdapter {

    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    private Entity focusEntity;
    private Color backgroundColor;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth()*4;
        float h = Gdx.graphics.getHeight()*4;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        tiledMap = new TmxMapLoader().load("gameart2d-desert.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        
        //Player x & y coordinates needed:
            //Moves the camera by playerspeed left or right:
        PositionPart entityPos = focusEntity.getPart(PositionPart.class);
        float entityX = entityPos.getX();
        float entityY = entityPos.getY();
        camera.position.set(entityX, entityY, 0);
        //camera.translate(entityX - camera.position.x, camera.position.y - entityY); //takes the difference between the players position and the cameras position.
    }
    
    public void setFocusEntity(Entity entity){
        this.focusEntity = entity;
    }
    
    public void setBackgroudColor(Color color){
        this.backgroundColor = color;
    }
}
