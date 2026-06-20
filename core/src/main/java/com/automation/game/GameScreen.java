package com.automation.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    // Reference to main for switching screens and using the asset manager
    Main main;

    // Screen Objects
    SpriteBatch spriteBatch;
    Viewport viewport;
    Camera camera;

    // Game Objects
    GameState gameState;

    public GameScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        // Instantiate the objects
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 480, camera);

        // Initialize the game state
        gameState = new GameState(main.getManager());

    }

    @Override
    public void render(float delta) {
        // Update the game state
        gameState.update(delta);

        // Clear the screen
        ScreenUtils.clear(Color.BLACK);

        // Apply the viewport and set the projection matrix from the camera
        viewport.apply();
        spriteBatch.setProjectionMatrix(camera.combined);

        // Begin the sprite batch and make the game draw call
        spriteBatch.begin();
        gameState.drawWorld(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }

    @Override
    public void dispose() {
        // Clean up objects
        spriteBatch.dispose();
    }
}
