/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

/**
 *
 * @author ahmadhamid
 */
public class Event implements Serializable {

    private final Entity source;

    public Event(Entity source) {
        this.source = source;
    }

    public Entity getSource() {
        return source;
    }
}
