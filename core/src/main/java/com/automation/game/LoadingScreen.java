package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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
        background = new Texture(Gdx.files.internal("loading_screen.png"));

        // TODO: Make this a more robust loader so loading a bunch of textures is easy
        // Queue the textures to be loaded
        main.getManager().load("BlankTile.png", Texture.class);
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
