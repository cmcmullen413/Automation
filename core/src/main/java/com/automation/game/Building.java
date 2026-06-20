package com.automation.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Abstract class for representing any building
 */
public abstract class Building {
    // Holds the path the assets folder for the building
    protected final String spritePath;

    TextureRegion currentTexture;

    int x;
    int y;

    public Building(int x, int y) {
        spritePath = "assets/buildings/" + this.getClass().getSimpleName() + "/";
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    /**
     * Draws the building onto the provided sprite batch
     * @param spriteBatch
     */
    public void draw(SpriteBatch spriteBatch, float camX, float camY, float camZoom) {
        float screenX = (x - y) * (Tile.TILE_WIDTH/2) * camZoom - camX;
        float screenY = (x + y) * (Tile.TILE_HEIGHT/2) * camZoom - camY;
        spriteBatch.draw(currentTexture, screenX, screenY, Tile.TILE_WIDTH*camZoom, Tile.TILE_HEIGHT*camZoom);
    }
}
