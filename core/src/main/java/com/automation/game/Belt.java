package com.automation.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Belt extends Building {

    AssetManager manager;
    Texture texture;

    // The direction the belt is facing
    private static final String[] DIRECTIONS = { "Southwest", "Northwest", "Northeast", "Southeast" };
    // 0 -> Southwest, 1-> Northwest, 2 -> Northeast, 3 -> Southeast
    int facing;

    // The number of ticks to stay in each animation frame for
    private static final int TICKS_PER_ANIMATION = 10;
    // The texture coords of each frame of the animation
    private static final int[][] TEX_COORDS = { {8, 15}, {72, 15}, {136, 15}, {200, 15} };
    private static final int TEX_SIZE_X = 46;
    private static final int TEX_SIZE_Y = 33;
    // Which step of the animation the belt is in
    private static int animationStep = 0;
    // The counter for when the animation should roll over
    private static int animationCounter = 0;

    public Belt(AssetManager manager, int x, int y) {
        super(x, y, TEX_SIZE_X, TEX_SIZE_Y);
        facing = 0;

        this.manager = manager;
        texture = manager.get(spritePath + DIRECTIONS[facing] + "/single.png");
        currentTexture = new TextureRegion(texture, TEX_COORDS[animationStep][0], TEX_COORDS[animationStep][1], TEX_SIZE_X, TEX_SIZE_Y);
    }

    @Override
    public void update() {
        // 6 times per second cycle to the next texture in the animation
        currentTexture = new TextureRegion(texture, TEX_COORDS[animationStep][0], TEX_COORDS[animationStep][1], TEX_SIZE_X, TEX_SIZE_Y);
    }

    @Override
    public boolean rotate() {
        // Update facing
        facing += 1;
        if (facing >= 4) { facing = 0; }
        // Update the texture
        texture = manager.get(spritePath + DIRECTIONS[facing] + "/single.png");
        currentTexture = new TextureRegion(texture, TEX_COORDS[animationStep][0], TEX_COORDS[animationStep][1], TEX_SIZE_X, TEX_SIZE_Y);
        return true;
    }

    /**
     * Called once a tick to update the global animation step for all belts
     */
    public static void updateAnimation() {
        animationCounter += 1;
        if (animationCounter >= TICKS_PER_ANIMATION) {
            animationStep += 1;
            if (animationStep >= 4) { animationStep = 0; }
            animationCounter = 0;
        }
    }
}
