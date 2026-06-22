package com.automation.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile extends WorldObject {
    // A reference to the asset manager to grab textures
    protected final Texture texture;
    protected TextureRegion texRegion;

    public Tile(AssetManager manager, int x, int y) {
        super(x, y);
        texture = manager.get("assets/tiles/" + this.getClass().getSimpleName() + ".png");
    }

    public void draw(SpriteBatch spriteBatch, float camX, float camY, float camZoom) {
        float screenX = (x - y) * (TILE_WIDTH/2) * camZoom - camX;
        float screenY = (x + y) * (TILE_HEIGHT/2) * camZoom - camY;
        spriteBatch.draw(texRegion, screenX, screenY, TILE_WIDTH*camZoom, TILE_HEIGHT*camZoom);
    }
}
