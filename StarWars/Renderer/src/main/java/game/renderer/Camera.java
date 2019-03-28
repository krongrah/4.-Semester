/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import data.GameData;

/**
 *
 * @author Krongrah
 */
public class Camera {

    private OrthographicCamera cam;

    public Camera() {
        cam = new OrthographicCamera(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    public Matrix4 getProjectionMatrix() {
        return cam.combined;
    }

    /**
     * sets the angle of the camera to the given angle. don't use, doesn't
     * update the camera.
     *
     * @param angle the new angle, given in degrees.
     */
    private void setCameraAngle(float angle) {
        cam.up.set(0, 1, 0);
        cam.direction.set(0, 0, -1);
        cam.rotate(angle);

    }

    private void setCamera(float x, float y, float angle) {
        setCameraAngle(-angle / 3.1415f * 180);
        cam.position.set(x, y, 0);
        cam.update();
    }

    public void update(GameData gameData) {
        setCamera(gameData.getFocusX(), gameData.getFocusY(), 0);

    }
}
