package com.automation.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Belt extends Building {

    Texture texture;

    // The direction the belt is facing
    private static final String[] DIRECTIONS = { "Southwest", "Northwest", "Northeast", "Southeast" };
    // 0 -> Southwest, 1-> Northwest, 2 -> Northeast, 3 -> Southeast
    int facing;

    // The number of ticks to stay in each animation frame for
    private static final int TICKS_PER_ANIMATION = 15;
    // The texture coords of each frame of the animation
    private static final int[][] TEX_COORDS = { {8, 15}, {72, 15}, {136, 15}, {200, 15} };
    private static final int TEX_SIZE_X = 46;
    private static final int TEX_SIZE_Y = 33;
    // Which step of the animation the belt is in
    // TODO make this global so all belts line up
    int animationStep;
    // The counter for when the animation should roll over
    int animationCounter;

    public Belt(AssetManager manager, int x, int y) {
        super(x, y, TEX_SIZE_X, TEX_SIZE_Y);
        facing = 0;
        animationStep = 0;
        animationCounter = 0;

        texture = manager.get(spritePath + DIRECTIONS[facing] + "/single.png");
        currentTexture = new TextureRegion(texture, TEX_COORDS[animationStep][0], TEX_COORDS[animationStep][1], TEX_SIZE_X, TEX_SIZE_Y);
    }

    @Override
    public void update() {
        // 4 times per second cycle to the next texture in the animation
        animationCounter += 1;
        if (animationCounter >= TICKS_PER_ANIMATION) {
            animationStep += 1;
            if (animationStep >= 4) { animationStep = 0; }
            currentTexture = new TextureRegion(texture, TEX_COORDS[animationStep][0], TEX_COORDS[animationStep][1], TEX_SIZE_X, TEX_SIZE_Y);
            animationCounter = 0;
        }
    }
}
