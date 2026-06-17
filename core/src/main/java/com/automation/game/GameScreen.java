package com.automation.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    // Screen Objects
    SpriteBatch spriteBatch;
    Viewport viewport;
    Camera camera;

    // Game Objects
    GameState gameState;

    @Override
    public void show() {
        // Instantiate the objects
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 480, camera);

        gameState = new GameState();
    }

    @Override
    public void render(float delta) {
        // Update the game state
        gameState.update(delta);

        // Apply the viewport and set the projection matrix from the camera
        viewport.apply();
        spriteBatch.setProjectionMatrix(camera.combined);

        // Begin the sprite batch and make draw calls
        spriteBatch.begin();
        gameState.drawWorld(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        // Clean up objects
        spriteBatch.dispose();
    }

    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }
}
