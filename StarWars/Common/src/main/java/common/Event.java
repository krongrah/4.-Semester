/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

/**
 * Class is used to hold source of events
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public class Event implements Serializable {

    private final Entity source;

    /**
     * Constructor for the Event
     *
     * @param source Is the source for the class to hold
     */
    public Event(Entity source) {
        this.source = source;
    }

    /**
     *
     * @return the source of the event
     */
    public Entity getSource() {
        return source;
    }
}
