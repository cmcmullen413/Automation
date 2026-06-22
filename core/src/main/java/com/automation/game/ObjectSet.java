package com.automation.game;

import java.util.TreeSet;

/**
 * Custom implementation of TreeSet that adds functionality for getting objects based on their
 * x and y position. Used for keeping buildings and such in the correct order to display them,
 * but also be able to get specific ones out to update or remove in some way.
 */
public class ObjectSet<T extends WorldObject> extends TreeSet<T> {
    /**
     * Gets the world object at the specified position
     * @param x
     * @param y
     * @return Returns the object if it is found or null is it does not exist
     */
    public T get(int x, int y) {
        // TODO
        return null;
    }
}
