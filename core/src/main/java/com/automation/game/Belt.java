package com.automation.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Belt extends Building {

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

    AssetManager manager;
    Texture texture;

    // The direction the belt is facing
    private static final String[] DIRECTIONS = { "Southwest", "Northwest", "Northeast", "Southeast" };
    // 0 -> Southwest, 1-> Northwest, 2 -> Northeast, 3 -> Southeast
    int facing;

    // TODO: Implement curves and some kind of check for if the belt actually faces into this one
    // The type of belt it should be depending on if it has neighbors facing towards and from it
    private static final String[] name = {};
    // Which neighbors the belt has
    Belt rearNeighbor;
    Belt frontNeighbor;
    Belt leftNeighbor;
    Belt rightNeighbor;

    public Belt(AssetManager manager, int x, int y) {
        super(x, y, TEX_SIZE_X, TEX_SIZE_Y);
        facing = 0;

        this.manager = manager;
        updateTexture();
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

        // Update the neighbor values
        Belt tempFront = frontNeighbor;
        frontNeighbor = rightNeighbor;
        rightNeighbor = rearNeighbor;
        rearNeighbor = leftNeighbor;
        leftNeighbor = tempFront;

        // Update the texture
        updateTexture();
        return true;
    }

    public void updateNeighbor(Belt neighbor, int neighborX, int neighborY) {
        // Find the correct side of this to set the neighbor to
        int deltaX = x-neighborX;
        int deltaY = y-neighborY;
        // This depends on the x and y value passed in as well as the direction this is currently facing
        if (facing == 0) {
            if (deltaX < 0) { frontNeighbor = neighbor; }
            else if (deltaX > 0) { rearNeighbor = neighbor; }
            else if (deltaY < 0) { leftNeighbor = neighbor; }
            else if (deltaY > 0) { rightNeighbor = neighbor; }
        } else if (facing == 1) {
            if (deltaX < 0) { leftNeighbor = neighbor; }
            else if (deltaX > 0) { rightNeighbor = neighbor; }
            else if (deltaY < 0) { rearNeighbor = neighbor; }
            else if (deltaY > 0) { frontNeighbor = neighbor; }
        } else if (facing == 2) {
            if (deltaX < 0) { rearNeighbor = neighbor; }
            else if (deltaX > 0) { frontNeighbor = neighbor; }
            else if (deltaY < 0) { rightNeighbor = neighbor; }
            else if (deltaY > 0) { leftNeighbor = neighbor; }
        } else if (facing == 3) {
            if (deltaX < 0) { rightNeighbor = neighbor; }
            else if (deltaX > 0) { leftNeighbor = neighbor; }
            else if (deltaY < 0) { frontNeighbor = neighbor; }
            else if (deltaY > 0) { rearNeighbor = neighbor; }
        }
        updateTexture();
    }

    /**
     * Updates the current texture for this block. Should be called each time a change is made
     * to anything that determines texture such as animationStep or facing
     */
    private void updateTexture() {
        // Get the correct type of belt (single, end, start, etc.)
        String beltType;
        // TODO Implement curves
        // If no front or rear neighbors, it is single
        if (frontNeighbor != null && rearNeighbor != null) { beltType = "single"; }
        // If just fron neighbor, it is start
        else if (rearNeighbor != null) { beltType = "start"; }
        // If just read neighbor, it is end
        else if (frontNeighbor != null) { beltType = "end"; }
        // Otherwise it is a middle
        else { beltType = "middle"; }

        texture = manager.get(spritePath + DIRECTIONS[facing] + "/" + beltType + ".png");
        currentTexture = new TextureRegion(texture, TEX_COORDS[animationStep][0], TEX_COORDS[animationStep][1], TEX_SIZE_X, TEX_SIZE_Y);
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
