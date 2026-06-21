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
    int buildingSizeX;
    int buildingSizeY;
    int buildingOffsetX;
    int buildingOffsetY;

    int x;
    int y;

    public Building(int x, int y, int buildingSizeX, int buildingSizeY) {
        spritePath = "assets/buildings/" + this.getClass().getSimpleName() + "/";
        this.buildingSizeX = buildingSizeX;
        this.buildingSizeY = buildingSizeY;
        buildingOffsetX = (Tile.TILE_WIDTH - buildingSizeX)/2;
        buildingOffsetY = (Tile.TILE_HEIGHT - buildingSizeY)/2;

        this.x = x;
        this.y = y;
    }

    public abstract void update();

    /**
     * Draws the building onto the provided sprite batch
     * @param spriteBatch
     */
    public void draw(SpriteBatch spriteBatch, float camX, float camY, float camZoom) {
        float realX = x+ 1f*buildingOffsetX/Tile.TILE_WIDTH;
        // TODO Figure out if centering on the y is actually needed because it doesn't seem like it
        float realY = y;// + 1f*buildingOffsetY/Tile.TILE_HEIGHT;
        float screenX = (realX - realY) * (Tile.TILE_WIDTH/2) * camZoom - camX;
        float screenY = (realX + realY) * (Tile.TILE_HEIGHT/2) * camZoom - camY;
        spriteBatch.draw(currentTexture, screenX, screenY, buildingSizeX*camZoom, buildingSizeY*camZoom);
    }
}
