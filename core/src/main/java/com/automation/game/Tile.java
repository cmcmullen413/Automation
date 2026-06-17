package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Tile {
    private Texture texture;

    public Tile(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }
}
