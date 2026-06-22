package com.automation.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Dummy class used for finding elements with specific coordinates in a set.
 * Exists purely to be able to create a new WorldObject with reduced overhead of creating a specific one
 */
public class DummyObject extends WorldObject {

    public DummyObject(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float camX, float camY, float camZoom) {

    }
}
