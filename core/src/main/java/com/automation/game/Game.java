package com.automation.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game {
    private static int WORLD_X = 10, WORLD_Y = 10;
    private static int TILE_SIZE = 50;

    private Tile[][] tiles;

    public Game() {
        initialize();
    }

    public void drawWorld(SpriteBatch spriteBatch, float deltaTime) {
        // Begin the sprite batch and draw
        spriteBatch.begin();

        // Draw each tile at its spot in the world
        for (int y = 0; y < WORLD_Y; y++) {
            for (int x = 0; x < WORLD_X; x++) {
                spriteBatch.draw(tiles[x][y].getTexture(), x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        spriteBatch.end();
    }

    private void initialize() {
        // Fill an X by Y array with blank tiles
        tiles = new Tile[WORLD_X][WORLD_Y];
        for (int y = 0; y < WORLD_Y; y++) {
            for (int x = 0; x < WORLD_X; x++) {
                tiles[x][y] = new BlankTile();
            }
        }
    }
}
