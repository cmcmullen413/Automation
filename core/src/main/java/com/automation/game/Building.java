package com.automation.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Abstract class for representing any building
 */
public abstract class Building {
    // Holds the path the assets folder for the building
    protected final String spritePath;

    public Building() {
        spritePath = "assets/buildings/" + this.getClass().getSimpleName();
    }

    /**
     * Draws the building onto the provided sprite batch
     * @param spriteBatch
     */
    public abstract void draw(SpriteBatch spriteBatch);
}
