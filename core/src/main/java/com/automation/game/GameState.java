package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

public class GameState {
    private static final int WORLD_X = 10, WORLD_Y = 10;

    // Reference to the asset manager for textures
    AssetManager manager;

    // List of all the tiles in the world
    Set<Tile> tiles;
    // List of all the buildings in the world
    Set<Building> buildings;

    // The camera controlled by the player
    GameCamera camera;
    // Where in the game world the cursor points at
    int pointX;
    int pointY;

    // Timer to keep track of when a game tick should happen
    float tickTimer;

    public GameState(AssetManager manager) {
        this.manager = manager;
        camera = new GameCamera();
        tickTimer = 0f;

        // Active the input handler
        InputHandler inputHandler = new InputHandler(camera);
        Gdx.input.setInputProcessor(inputHandler);

        initialize();
    }

    /**
     * Updates the game state. Should be called once a frame
     * @param delta
     */
    public void update(float delta) {
        // Update input
        InputHandler.update();
        // Update camera
        camera.update(delta);
        // Update where the player is pointing to
        updatePlayerPointer(InputHandler.mouseX, InputHandler.mouseY, camera.getX(), camera.getY(), camera.getZoom());

        // TODO: Finish
        // If the player left clicks, place the currently selected building at the cursor location
        // If the player right clicks, delete the building at the cursor location
        if (InputHandler.leftClick) {

        }

        // Update core game mechanics on a fixed time step ( 60/sec)
        tickTimer += delta;
        if (tickTimer >= 1/60f) {
            gameTick();
            tickTimer -= 1/60f;
        }
    }

    /**
     * Draws the world onto the passed in sprite batch
     * @param spriteBatch
     */
    public void drawWorld(SpriteBatch spriteBatch) {
        // TODO: Improve rendering to only draw tiles that will be on screen

        // Draw each tile
        for (Tile tile : tiles) {
            tile.draw(spriteBatch, camera.getX(), camera.getY(), camera.getZoom());
        }
        // Draw each building
        for (Building building : buildings) {
            building.draw(spriteBatch, camera.getX(), camera.getY(), camera.getZoom());
        }
    }

    /**
     * Initializes the game state upon loading the game
     */
    private void initialize() {
        // Fill the tiles array with the starting world tiles
        tiles = new TreeSet<>();
        buildings = new TreeSet<>();
        for (int y = -WORLD_Y; y < WORLD_Y; y++) {
            for (int x = -WORLD_X; x < WORLD_X; x++) {
                // Fill the world with stone
                tiles.add(new StoneTile(manager, x, y));
                // Place belts along the diagonal
                if (x == y) {
                    buildings.add(new Belt(manager, x, y));
                }
            }
        }
    }

    /**
     * Runs a game tick update.
     * Should be called 60 times per second
     */
    private void gameTick() {
        for (Building building : buildings) {
            building.update();
        }
    }

    private void updatePlayerPointer(float mouseX, float mouseY, float camX, float camY, float camZoom) {
        pointX = 0;
        pointY = 0;
        // TODO: Finish
        // Basically just reverses the same transforms done in the draw function for Tile
        //pointX = (int) (InputHandler.mouseX + camera.getX()) / (camera.getZoom() * );
    }
}
