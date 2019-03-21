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
public enum Planets {
    TATOOINE("Tatooine"), HOTH("Hoth"), DEATHSTAR("Deathstar");
    private String name;

    private Planets(String name) {
        this.name = name;
    }

}
