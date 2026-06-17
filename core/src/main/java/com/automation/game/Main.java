package com.automation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class Main extends Game {

    // Asset manager for texture loading
    private AssetManager manager;

    @Override
    public void create() {
        // Instantiate an asset manager
        manager = new AssetManager();

        // Open the loading screen
        setScreen(new LoadingScreen(this));
    }

    public AssetManager getManager() {
        return manager;
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
