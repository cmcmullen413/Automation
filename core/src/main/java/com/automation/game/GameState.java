package com.automation.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private static final int WORLD_X = 10, WORLD_Y = 10;

    // Reference to the asset manager for textures
    AssetManager manager;

    // List of all the tiles in the world
    List<Tile> tiles;

    public GameState(AssetManager manager) {
        this.manager = manager;
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
                tiles.add(new BlankTile(manager, x, y));
            }
        }
    }
}
