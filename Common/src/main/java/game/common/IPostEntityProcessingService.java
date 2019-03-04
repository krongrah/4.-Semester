/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.common;

/**
 *
 * @author ahmadhamid
 */
public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
