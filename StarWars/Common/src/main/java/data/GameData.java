/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import common.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class is used to hold general and common Data of the game
 *
 * @author Ahmad Hamid
 * @author Mikkel Brinchs Larsen
 * @author Kasper Schødts
 * @author Sebastian Christiansen
 * @author Kian Barrett Javidan
 * @author Andreas Bøgh Mølgaard-Andersen
 */
public class GameData {

    private float delta;
    private int displayWidth;
    private int displayHeight;
    private float focusX=22*32;
    private float focusY=(39*32)+16;
    private final GameKeys keys = new GameKeys();
    private List<Event> events = new CopyOnWriteArrayList<>();

    /**
     * Appends an event to the ArrayList
     *
     * @param e Is the event to be appended to the ArrayList
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Removes an event from the ArrayList
     *
     * @param e is the Event to be removed
     */
    public void removeEvent(Event e) {
        events.remove(e);
    }

    /**
     *
     * @return an ArrayList of all events in the game
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     *
     * @return an instance of the GameKeys object
     */
    public GameKeys getKeys() {
        return keys;
    }

    /**
     * Sets the new delta
     *
     * @param delta new delta to be set
     */
    public void setDelta(float delta) {
        this.delta = delta;
    }

    /**
     * Is the time since last update
     *
     * @return the float delta which is the difference in time since the last
     * update
     */
    public float getDelta() {
        return delta;
    }

    /**
     * Is used to set the display width
     *
     * @param width Is the ned width to be set
     */
    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    /**
     * Is used to get the width of the display in pixels
     *
     * @return The width of the display
     */
    public int getDisplayWidth() {
        return displayWidth;
    }

    /**
     * Sets the height to the given value
     *
     * @param height is the value to be set
     */
    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    /**
     * Is used to get the height of the display in pixels
     *
     * @return the height of the display
     */
    public int getDisplayHeight() {
        return displayHeight;
    }

    /**
     * Gets all events in the game
     *
     * @param <E> type of Event to return a list of
     * @param type Is the type of Event to return
     * @param sourceID Is the sourceID of the event
     * @return an ArrayList of all the events
     */
    public <E extends Event> List<Event> getEvents(Class<E> type, String sourceID) {
        List<Event> r = new ArrayList();
        for (Event event : events) {
            if (event.getClass().equals(type) && event.getSource().getID().equals(sourceID)) {
                r.add(event);
            }
        }

        return r;
    }

    public float getFocusX() {
        return focusX;
    }

    public void setFocusX(float focusX) {
        this.focusX = focusX;
    }

    public void setFocusY(float focusY) {
        this.focusY = focusY;
    }

    public float getFocusY() {
        return focusY;
    }
}
