package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

public class GameState {
    private static final int WORLD_X = 10, WORLD_Y = 10;

    // Reference to the asset manager for textures
    AssetManager manager;

    // List of all the tiles in the world
    ObjectSet<Tile> tiles;
    // List of all the buildings in the world
    ObjectSet<Building> buildings;

    // The camera controlled by the player
    GameCamera camera;

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

        // If the player is left clicking, place the currently selected building at the cursor location
        // If the player is right clicking, delete the building at the cursor location
        if (InputHandler.leftHold) {
            // TODO: Implement building selection
            // Currently just places a belt

            // Get the location where the pointer is
            int[] pointer = getPlayerPointer();

            // Attempt to add a new building
            // If there is already one there, the set will not be able to add it
            // If there is no building there, the set will add it
            Belt newBuilding = new Belt(manager, pointer[0], pointer[1]);
            buildings.add(newBuilding);

            // Update which neighbors the belt has as well as the neighbors around it
            updateBeltNeighbors((Belt) newBuilding, pointer[0], pointer[1]);

        } else if (InputHandler.rightHold) {
            // Get the location where the pointer is
            int[] pointer = getPlayerPointer();

            // Call buildings.remove with a DummyObject
            // If there is no building at that space, nothing will happen
            buildings.remove(new Building(pointer[0], pointer[1], 0, 0) {@Override public void update() {}});
            // Update the neighbors of the surrounding belts
            updateBeltNeighbors(null, pointer[0], pointer[1]);
        }
        // If the player pressed R, rotate the building at the position if there is one
        else if (InputHandler.keyPresses.get(Input.Keys.R)) {
            // Get the mouse position
            int[] pointer = getPlayerPointer();

            // DEBUGGING
            System.out.printf("Pointer at (%d, %d)\n", pointer[0], pointer[1]);

            // Get the building at the position if there is one
            Building building = buildings.get(pointer[0], pointer[1]);
            // If there is no building at the position, do nothing
            if (building != null) {

                // DEBUGGING
                System.out.printf("Building at (%d, %d)\n", pointer[0], pointer[1]);

                // If there is a building, attempt to rotate it
                if (!building.rotate()) {
                    // If the building could not be rotated, print a message out
                    // TODO: Make this print to the screen in some way so the player can see
                    System.out.println("Building cannot be rotated");
                }
            }
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
        tiles = new ObjectSet<>();
        buildings = new ObjectSet<>();
        // Fill the world with stone tiles
        for (int y = -WORLD_Y; y < WORLD_Y; y++) {
            for (int x = -WORLD_X; x < WORLD_X; x++) {
                tiles.add(new StoneTile(manager, x, y));
            }
        }
    }

    /**
     * Runs a game tick update.
     * Should be called 60 times per second
     */
    private void gameTick() {
        // Update the Belt global animation variable
        Belt.updateAnimation();

        for (Building building : buildings) {
            building.update();
        }
    }

    /**
     * Reverses the transforms done with displaying objects to give the world coordinates of where the player is pointing
     * @return An int array of length 2 [x, y]
     */
    private int[] getPlayerPointer() {
        // Undo to camera shift on the x and y position of the mouse
        float x = (InputHandler.mouseX - camera.getX()) / camera.getZoom();
        float y = (InputHandler.mouseY - camera.getY()) / camera.getZoom();
        // Solve for worldX and worldY
        float worldX = (y+x/2)/WorldObject.TILE_HEIGHT - 0.5f;
        float worldY = (y-x/2)/WorldObject.TILE_HEIGHT + 0.5f;
        // Round them down to get integers
        return new int[] { (int) Math.floor(worldX), (int) Math.floor(worldY) };
    }

    /**
     * Updates the neighbors surrounding a belt when placing or removing it
     * @param belt The belt placed or null if it was removed
     * @param x The x position where the change was made
     * @param y The y position where the change was made
     */
    private void updateBeltNeighbors(Belt belt, int x, int y) {
        // Get the building surrounding this one
        Building[] neighbors = {
            buildings.get(x-1, y),
            buildings.get(x, y+1),
            buildings.get(x+1, y),
            buildings.get(x, y-1)
        };
        // For each one, if it is a belt, update it's and the new belts neighbors
        for (Building neighbor : neighbors) {
            if (neighbor != null && neighbor.getClass() == Belt.class) {
                ((Belt) neighbor).updateNeighbor(belt, x, y);
                // If the belt was removed, belt will be null so skip updating it
                if (belt != null) { belt.updateNeighbor((Belt) neighbor, neighbor.x, neighbor.y); }
            }
        }
    }
}
