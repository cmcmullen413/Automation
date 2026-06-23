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

    // The size of the building in number of tiles
    int buildingSize;


    public Building(int x, int y, int buildingSize) {
        super(x, y);
        spritePath = "assets/buildings/" + this.getClass().getSimpleName() + "/";
        this.buildingSize = buildingSize;
    }

    /**
     * Updates the building's state. Should be called once a game tick
     */
    public abstract void update();

    /**
     * Rotates the building if it can be
     * @return Returns whether the building was rotated
     */
    public boolean rotate() {
        return false;
    }

    /**
     * Draws the building onto the provided sprite batch
     * @param spriteBatch
     */
    public void draw(SpriteBatch spriteBatch, float camX, float camY, float camZoom) {
        float buildingOffsetX = (buildingSize*TILE_WIDTH - currentTexture.getRegionWidth())/2f;
        float realX = x + buildingOffsetX/Tile.TILE_WIDTH;
        // TODO: Figure out if centering on the y is actually needed because it doesn't seem like it
        float buildingOffsetY = (TILE_HEIGHT - currentTexture.getRegionHeight())/2f;
        float realY = y;// + 1f*buildingOffsetY/Tile.TILE_HEIGHT;
        float screenX = (realX - realY) * (TILE_WIDTH/2f) * camZoom - camX;
        float screenY = (realX + realY) * (TILE_HEIGHT/2f) * camZoom - camY;
        spriteBatch.draw(currentTexture, screenX, screenY, currentTexture.getRegionWidth()*camZoom, currentTexture.getRegionHeight()*camZoom);
    }
}
