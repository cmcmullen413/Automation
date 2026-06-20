package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Objects;

public class LoadingScreen implements Screen {
    // Reference to main for switch screens and using the asset manager
    Main main;

    // Screen Objects
    SpriteBatch spriteBatch;
    Texture background;

    public LoadingScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        // Instantiate a sprite batch
        spriteBatch = new SpriteBatch();
        // Load the background texture
        background = new Texture("loading_screen.png");

        // Queue the textures to be loaded
        queueFileLoad(main.getManager(), Gdx.files.internal("assets/"));
    }

    @Override
    public void render(float delta) {
        // Check if the manager is done loading
        // If it is, switch to the game screen
        if (main.getManager().update()) {
            main.setScreen(new GameScreen(main));
        }

        // Clear the screen
        ScreenUtils.clear(Color.BLACK);

        // Begin sprite batch and draw the background
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();
    }

    /**
     * Recursively queue all the asset files to be loaded by the asset manager
     * @param manager
     * @param file
     */
    private void queueFileLoad(AssetManager manager, FileHandle file) {
        // If the file isn't a directory, load the file if it is a png
        if (!file.isDirectory()) {
            if (file.extension().equals("png")) {
                manager.load(file.path(), Texture.class);
            }
            return;
        }
        // If it is a directory, recurse into the subfiles
        for (FileHandle subFile : file.list()) {
            queueFileLoad(manager, subFile);
        }
    }

    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        background.dispose();
    }
}
