package com.automation.game;

import java.util.SortedSet;
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
        // If the set is empty, return immediately
        if (this.isEmpty()) { return null; }

        return getHelper(this, x, y);
    }

    /**
     * Recursive helper method for get
     * @param set
     * @param x
     * @param y
     * @return
     */
    private T getHelper(SortedSet<T> set, int x, int y) {
        // Get the first of the set and compare it to the x and y
        T first = set.getFirst();
        int comparison = first.compareTo(x, y);

        // Base case
        // If first has matching x and y, return it
        if (comparison == 0) { return first; }

        // Recursive case
        // If first > x, recurse down the head side of the tree
        if (comparison > 0) {
            return getHelper(this.headSet(first), x, y);
        }
        // Otherwise recurse down the tail side
        return getHelper(this.tailSet(first), x, y);

    }
}
