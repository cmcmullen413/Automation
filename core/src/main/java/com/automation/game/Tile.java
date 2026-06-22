package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile implements Comparable<Tile> {
    // The default size of a tile
    public static final int TILE_WIDTH = 52;
    public static final int TILE_HEIGHT = TILE_WIDTH/2;

    // A reference to the asset manager to grab textures
    protected final Texture texture;
    protected TextureRegion texRegion;

    protected final int x;
    protected final int y;

    public Tile(AssetManager manager, int xPos, int yPos) {
        texture = manager.get("assets/tiles/" + this.getClass().getSimpleName() + ".png");
        x = xPos;
        y = yPos;
    }

    public void draw(SpriteBatch spriteBatch, float camX, float camY, float camZoom) {
        float screenX = (x - y) * (TILE_WIDTH/2) * camZoom - camX;
        float screenY = (x + y) * (TILE_HEIGHT/2) * camZoom - camY;
        spriteBatch.draw(texRegion, screenX, screenY, TILE_WIDTH*camZoom, TILE_HEIGHT*camZoom);
    }

    @Override
    public int compareTo(Tile other) {
        // Sort by x first, then y
        // Higher x or y should be ordered lower so when a set of buildings is iterated through
        // They can be drawn from back right to front left to display them properly
        if (this.x != other.x) { return this.x < other.x ? 1 : -1; }
        if (this.y != other.y) { return this.y < other.y ? 1 : -1; }
        // If the x and y are the same they are in the same square
        return 0;
    }
}
