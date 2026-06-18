package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile {
    // The default size of a tile
    public static final int TILE_SIZE = 25;

    // A reference to the asset manager to grab textures
    private final AssetManager manager;

    private int tileX;
    private int tileY;

    public Tile(AssetManager manager, int xPos, int yPos) {
        this.manager = manager;
        tileX = xPos;
        tileY = yPos;
    }

    public void draw(SpriteBatch spriteBatch, float camX, float camY) {
        spriteBatch.draw(manager.get("assets/tiles/" + this.getClass().getSimpleName() + ".png", Texture.class),
            TILE_SIZE*tileX-camX, TILE_SIZE*tileY-camY, TILE_SIZE, TILE_SIZE);
    }
}
