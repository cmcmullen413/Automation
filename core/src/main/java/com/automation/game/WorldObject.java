package com.automation.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Abstract class for defining an object that can be drawn into the world. This includes things like tiles, buildings, and items
 */
public abstract class WorldObject implements Comparable<WorldObject> {

    // The default size of a tile
    public static final int TILE_WIDTH = 52;
    public static final int TILE_HEIGHT = TILE_WIDTH/2;

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

    /**
     * Defines the natural ordering of WorldObjects. Objects are ordered from back to front in the world space
     * so they display overlapped correctly when drawn.
     * @param o the object to be compared.
     * @return
     */
    public int compareTo(WorldObject o) {
        return this.compareTo(o.x, o.y);
    }

    /**
     * Defines the natural ordering of WorldObjects. Objects are ordered from back to front in the world space
     * so they display overlapped correctly when drawn.
     * @param x the x position to compare to
     * @param y the y position to compare to
     * @return
     */
    public int compareTo(int x, int y) {
        // Sort by x first, then y
        // Higher x or y should be ordered lower so when a set of buildings is iterated through
        // They can be drawn from back right to front left to display them properly
        if (this.x != x) { return this.x < x ? 1 : -1; }
        if (this.y != y) { return this.y < y ? 1 : -1; }
        // If the x and y are the same they are in the same square
        return 0;
    }
}
