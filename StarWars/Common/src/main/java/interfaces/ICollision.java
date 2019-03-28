/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import enums.CollisionTypes;

/**
 *
 * @author Sebas
 */
public interface ICollision {
    public CollisionTypes getCollisionType();
    
    public void setCollision(CollisionTypes type);
            
}
