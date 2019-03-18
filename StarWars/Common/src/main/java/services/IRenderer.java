/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import data.GameData;
import data.World;

/**
 *
 * @author Sebas
 */
public interface IRenderer {

    public void render(World world, GameData gameData);

    public void setBackgroudColor(float r, float g, float b, float a);
}
