package game.renderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import data.GameData;
import data.World;
import services.IRenderer;

/**
 *
 * @author Krongrah
 */
public class Renderer implements IRenderer {

    private UnitDrawBoard board;
    private Camera cam;
    private MapDrawBoard map;
    private Color backgroundColor;

    public Renderer(World world) {
        board = new UnitDrawBoard();
        cam = new Camera();
        map = new MapDrawBoard(world);
        Gdx.graphics.setVSync(true);

    }

    @Override
    public void render(World world, GameData gameData) {
        //Long time=System.currentTimeMillis();
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a); //Gets the RGBA values of the backgound Color
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update(gameData);
        map.render(gameData, cam.getProjectionMatrix());
        board.draw(world, gameData, cam.getProjectionMatrix());
        //System.out.println("renderer: "+(System.currentTimeMillis()-time));
    }

    @Override
    public void setBackgroundColor(float r, float g, float b, float a) {
        this.backgroundColor = new Color(r / 255, g / 255, b / 255, a / 255);
    }

    @Override
    public void loadTexture(String path) {
        board.Load(path);
    }

}
