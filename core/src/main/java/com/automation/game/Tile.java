package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile {
    // The default size of a tile
    public static final int TILE_WIDTH = 50;
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
}
