package com.automation.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class GameState {
    private static final int WORLD_X = 10, WORLD_Y = 10;

    private ArrayList<Tile> tiles;

    public GameState() {
        initialize();
    }

    public void update(float deltaTime) {

    }

    public void drawWorld(SpriteBatch spriteBatch) {
        // Draw each tile
        for (Tile tile : tiles) {
            tile.draw(spriteBatch);
        }
    }

    private void initialize() {
        // Fill the tiles array with the starting world tiles
        tiles = new ArrayList<>(WORLD_X*WORLD_Y);
        for (int y = 0; y < WORLD_Y; y++) {
            for (int x = 0; x < WORLD_X; x++) {
                tiles.add(new BlankTile(x, y));
            }
        }
    }
}
