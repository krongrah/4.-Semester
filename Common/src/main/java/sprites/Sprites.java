/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprites;

/**
 *
 * @author Krongrah
 */
public enum Sprites {

    LUKE("Luke"), STORMTROOPER("Stormtrooper"), SNOWTROOPER("Snowtrooper"), DARTHVADER("Darthvader"),
    TUSKENRAIDER("Tuskenraider"), ATST("Atst");
   
    private String name;

    private Sprites(String name) {
        this.name = name;
    }

}
