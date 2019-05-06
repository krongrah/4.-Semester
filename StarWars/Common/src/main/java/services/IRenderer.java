/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import data.GameData;
import data.World;
import enums.State;
import java.util.List;
import Animation.Animation;

/**
 *
 * @author Sebas
 */
public interface IRenderer {

    public void render(World world, GameData gameData, State state);

    public void setBackgroundColor(float r, float g, float b, float a);

    public void loadTexture(List<Animation> animation);
}
