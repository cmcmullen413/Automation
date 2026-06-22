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

        // Set up for the first recursive call
        // Get the first element
        T first = this.getFirst();
        int comparison = first.compareTo(x, y);
        // If first is at the position or null, return it
        if (comparison == 0) { return first; }
        // Otherwise, recurse left or right depending on comparison
        if (comparison > 0) { return this.getHelper(this.headSet(first), first, x, y); }
        return this.getHelper(this.tailSet(first, false), first, x, y);
    }

    /**
     * Recursive helper method for get
     * @param set
     * @param x
     * @param y
     * @return
     */
    private T getHelper(SortedSet<T> set, T previous, int x, int y) {
        // If the passed set is empty, return null
        if (set.isEmpty()) { return null; }
        // Get the first of the set and compare it to the x and y
        T first = set.getFirst();

        int comparison = first.compareTo(x, y);
        // Base case
        // If first has matching x and y, return it
        if (comparison == 0) { return first; }

        // Recursive case
        // If first > x, recurse down the head side of the tree
        if (comparison > 0) {
            // If the previous call was down the tail side, subSet needs to be called instead of headSet
            if (previous.compareTo(first) < 0) { return getHelper(this.subSet(previous, false, first, false), first, x, y); }
            return getHelper(this.headSet(first), first, x, y);
        }
        // Otherwise recurse down the tail side
        // If the previous call was down the head side, subSet needs to be called instead of tailSet
        if (previous.compareTo(first) > 0) { return getHelper(this.subSet(first, false, previous, false), first, x, y); }
        return getHelper(this.tailSet(first, false), first, x, y);

    }
}
