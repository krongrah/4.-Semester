/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import data.GameData;
import services.IRenderer;
import interfaces.IWorld;
import worldHandling.MapDrawBoard;

/**
 *
 * @author Krongrah
 */
public class Renderer implements IRenderer{

    private UnitDrawBoard board;
    private Camera cam;
    private MapDrawBoard map;
    
    public Renderer(){
    board=new UnitDrawBoard();
    cam=new Camera();
    map=new MapDrawBoard();
    }
    
    @Override
    public void render(IWorld world, GameData gameData) {
        //Long time=System.currentTimeMillis();
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update(gameData);
        map.render(gameData, cam.getProjectionMatrix());
        board.draw(world,gameData, cam.getProjectionMatrix());
        //System.out.println("renderer: "+(System.currentTimeMillis()-time));
    }

    
}
