package com.automation.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Abstract class for representing any building
 */
public abstract class Building extends WorldObject {
    // Holds the path the assets folder for the building
    protected final String spritePath;

    protected TextureRegion currentTexture;
    int buildingSizeX;
    int buildingSizeY;
    int buildingOffsetX;
    int buildingOffsetY;

    public Building(int x, int y, int buildingSizeX, int buildingSizeY) {
        super(x, y);
        spritePath = "assets/buildings/" + this.getClass().getSimpleName() + "/";
        this.buildingSizeX = buildingSizeX;
        this.buildingSizeY = buildingSizeY;
        buildingOffsetX = (TILE_WIDTH - buildingSizeX)/2;
        buildingOffsetY = (TILE_HEIGHT - buildingSizeY)/2;
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
        float screenX = (realX - realY) * (TILE_WIDTH/2) * camZoom - camX;
        float screenY = (realX + realY) * (TILE_HEIGHT/2) * camZoom - camY;
        spriteBatch.draw(currentTexture, screenX, screenY, buildingSizeX*camZoom, buildingSizeY*camZoom);
    }
}
