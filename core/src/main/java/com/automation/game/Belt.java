package com.automation.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Belt extends Building {

    Texture texture;

    // The direction the belt is facing
    private static final String[] DIRECTIONS = { "Southeast", "Northeast", "Northwest", "Southwest" };
    // 0 -> Southeast, 1-> Northeast, 2 -> Northwest, 3 -> Southwest
    int facing;

    public Belt(AssetManager manager, int x, int y) {
        super(x, y);
        facing = 0;
        texture = manager.get(spritePath + DIRECTIONS[facing] + "/single.png");
        currentTexture = new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void update() {

    }
}
