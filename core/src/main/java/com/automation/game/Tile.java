package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile {
    // The default size of a tile
    protected static final int TILE_WIDTH = 50;
    protected static final int TILE_HEIGHT = TILE_WIDTH/2;

    // A reference to the asset manager to grab textures
    protected final Texture texture;

    protected final int x;
    protected final int y;

    public Tile(AssetManager manager, int xPos, int yPos) {
        texture = manager.get("assets/tiles/" + this.getClass().getSimpleName() + ".png");
        x = xPos;
        y = yPos;
    }

    public abstract void draw(SpriteBatch spriteBatch, float camX, float camY);
}
