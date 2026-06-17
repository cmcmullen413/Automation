package com.automation.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile {
    // The default size of a tile
    public static final int TILE_SIZE = 25;

    private Texture texture;

    private int tileX;
    private int tileY;

    public Tile(Texture texture, int xPos, int yPos) {
        this.texture = texture;
        tileX = xPos;
        tileY = yPos;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, TILE_SIZE*tileX, TILE_SIZE*tileY, TILE_SIZE, TILE_SIZE);
    }
}
