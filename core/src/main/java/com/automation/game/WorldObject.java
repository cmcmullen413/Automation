package com.automation.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Abstract class for defining an object that can be drawn into the world. This includes things like tiles, buildings, and items
 */
public abstract class WorldObject implements Comparable<WorldObject> {

    // The default size of a tile
    protected static final int TILE_WIDTH = 52;
    protected static final int TILE_HEIGHT = TILE_WIDTH/2;

    // The current texture used to display the object
    protected TextureRegion currentTexture;

    // The world coordinates of the object
    protected int x;
    protected int y;

    public WorldObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the object onto the given sprite batch
     * @param spriteBatch
     */
    public abstract void draw(SpriteBatch spriteBatch, float camX, float camY, float camZoom);

    @Override
    public boolean equals(Object o) {
        // If the passed object isn't a WorldObject, return false
        if (!o.getClass().equals(this.getClass())) { return false; }
        // Otherwise, return true iff the coordinates match up
        WorldObject obj = (WorldObject) o;
        return (this.x == obj.x) && (this.y == obj.y);
    }

    /**
     * Defines the natural ordering of WorldObjects. Objects are ordered from back to front in the world space
     * so they display overlapped correctly when drawn.
     * @param o the object to be compared.
     * @return
     */
    public int compareTo(WorldObject o) {
        // Sort by x first, then y
        // Higher x or y should be ordered lower so when a set of buildings is iterated through
        // They can be drawn from back right to front left to display them properly
        if (this.x != o.x) { return this.x < o.x ? 1 : -1; }
        if (this.y != o.y) { return this.y < o.y ? 1 : -1; }
        // If the x and y are the same they are in the same square
        return 0;
    }
}
