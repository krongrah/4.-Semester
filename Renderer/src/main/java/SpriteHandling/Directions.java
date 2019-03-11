/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpriteHandling;

/**
 *
 * @author Krongrah
 */
enum Directions {
    
    NORTH("North"), WEST("West"), SOUTH("South"), EAST("East");
    
    private String name;

    private Directions(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
    
    static Directions getDirection(float angle, float worldAngle){
        //todo make better
        float adjAngle=((angle-worldAngle)+(3.1415f/4))%(3.1415f*2);
        if(adjAngle<0){
        adjAngle+=3.1415f*2;
        }
        if(adjAngle>=3.1415f*1.5f){
        return EAST;
        }
        if(adjAngle>=3.1415f){
        return SOUTH;
        }
        if(adjAngle>=3.1415f/2){
        return WEST;
        }
        if(adjAngle>=0){
        return NORTH;
        }
        return NORTH;
    }
    
}
