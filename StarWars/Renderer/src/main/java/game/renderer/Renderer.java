package game.renderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import data.GameData;
import data.World;
import enums.State;
import java.util.List;
import services.IRenderer;
import Animation.Animation;

/**
 *
 * @author Krongrah
 */
public class Renderer implements IRenderer {

    private UnitDrawBoard board;
    private Camera cam;
    private MapDrawBoard map;
    private Color backgroundColor;
    SplashScreenDrawer ssd;
    private boolean loading;

    public Renderer(World world) {
        board = new UnitDrawBoard();
        cam = new Camera();
        map = new MapDrawBoard(world);
        Gdx.graphics.setVSync(true);
//        ssd = new SplashScreenDrawer("SplashScreen", 8);

    }

    @Override
    public void render(World world, GameData gameData, State state) {
        if (this.loading) {
            //DO NOTHING
        } else {

            if (state == State.PLAYSTATE) {

                Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a); //Gets the RGBA values of the backgound Color
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                Gdx.graphics.setVSync(false);

                cam.update(gameData);
                map.render(gameData, cam.getProjectionMatrix());
                board.draw(world, gameData, cam.getProjectionMatrix());

            } else if (state == State.SPLASHSTATE) {

                cam.update(gameData);

//                ssd.drawSplashScreen(world, gameData, cam.getProjectionMatrix());

            }
        }

    }

    @Override
    public void setBackgroundColor(float r, float g, float b, float a) {
        this.backgroundColor = new Color(r / 255, g / 255, b / 255, a / 255);
    }

    /**
     *
     * @param animation
     */
    @Override
    public void loadTexture(List<Animation> animation) {
        this.setIsLoading(true);
        board.Load(animation);
        this.setIsLoading(false);
    }

    private void setIsLoading(boolean b) {
        this.loading = b;
    }

}
