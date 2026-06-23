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
    private static final int FACING_SOUTHWEST = 0;
    private static final int FACING_NORTHWEST = 1;
    private static final int FACING_NORTHEAST = 2;
    private static final int FACING_SOUTHEAST = 3;
    int facing;

    // The type of belt it should be depending on if it has neighbors facing towards and from it
    private static final String[] name = {};
    // Which neighbors the belt has as well as if the neighbor actually face into this belt
    Belt frontNeighbor;
    boolean frontNeighborFacing;
    Belt rearNeighbor;
    boolean rearNeighborFacing;
    Belt leftNeighbor;
    boolean leftNeighborFacing;
    Belt rightNeighbor;
    boolean rightNeighborFacing;

    public Belt(AssetManager manager, int x, int y) {
        super(x, y, TEX_SIZE_X, TEX_SIZE_Y);
        facing = FACING_SOUTHWEST;

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
        if (facing >= 4) { facing = FACING_SOUTHWEST; }

        // Update the neighbor values
        Belt tempFront = frontNeighbor;
        boolean tempFrontFacing = frontNeighborFacing;
        frontNeighbor = rightNeighbor;
        frontNeighborFacing = rightNeighborFacing;
        rightNeighbor = rearNeighbor;
        rightNeighborFacing = rearNeighborFacing;
        rearNeighbor = leftNeighbor;
        rearNeighborFacing = leftNeighborFacing;
        leftNeighbor = tempFront;
        leftNeighborFacing = tempFrontFacing;

        // Update the texture
        updateTexture();

        return true;
    }

    public void updateNeighbor(Belt neighbor, int neighborX, int neighborY) {
        // Find the correct side of this to set the neighbor to
        int deltaX = neighborX - x;
        int deltaY = neighborY - y;
        // This depends on the x and y value passed in as well as the direction this is currently facing
        // Also update whether that belt facing into this one or not
        //
        // Whether the neighbor faces towards this belt or not
        boolean newNeighborFacing;
        if (deltaX < 0) {
            if (neighbor != null) { newNeighborFacing = neighbor.facing == FACING_NORTHEAST; }
            else { newNeighborFacing = false; }

            if (facing == FACING_SOUTHWEST) {
                frontNeighbor = neighbor;
                frontNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_NORTHWEST) {
                leftNeighbor = neighbor;
                leftNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_NORTHEAST) {
                rearNeighbor = neighbor;
                rearNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_SOUTHEAST) {
                rightNeighbor = neighbor;
                rightNeighborFacing = newNeighborFacing;
            }
        } else if (deltaX > 0) {
            if (neighbor != null) { newNeighborFacing = neighbor.facing == FACING_SOUTHWEST; }
            else { newNeighborFacing = false; }

            if (facing == FACING_SOUTHWEST) {
                rearNeighbor = neighbor;
                rearNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_NORTHWEST) {
                rightNeighbor = neighbor;
                rightNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_NORTHEAST) {
                frontNeighbor = neighbor;
                frontNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_SOUTHEAST) {
                leftNeighbor = neighbor;
                leftNeighborFacing = newNeighborFacing;
            }
        } else if (deltaY < 0) {
            if (neighbor != null) { newNeighborFacing = neighbor.facing == FACING_NORTHWEST; }
            else { newNeighborFacing = false; }

            if (facing == FACING_SOUTHWEST) {
                leftNeighbor = neighbor;
                leftNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_NORTHWEST) {
                rearNeighbor = neighbor;
                rearNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_NORTHEAST) {
                rightNeighbor = neighbor;
                rightNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_SOUTHEAST) {
                frontNeighbor = neighbor;
                frontNeighborFacing = newNeighborFacing;
            }
        } else if (deltaY > 0) {
            if (neighbor != null) { newNeighborFacing = neighbor.facing == FACING_SOUTHEAST; }
            else { newNeighborFacing = false; }

            if (facing == FACING_SOUTHWEST) {
                rightNeighbor = neighbor;
                rightNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_NORTHWEST) {
                frontNeighbor = neighbor;
                frontNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_NORTHEAST) {
                leftNeighbor = neighbor;
                leftNeighborFacing = newNeighborFacing;
            }
            else if (facing == FACING_SOUTHEAST) {
                rearNeighbor = neighbor;
                rearNeighborFacing = newNeighborFacing;
            }
        }

        // Update the new texture
        updateTexture();
    }

    /**
     * Updates the current texture for this block. Should be called each time a change is made
     * to anything that determines texture such as animationStep or facing
     */
    private void updateTexture() {
        // TODO: Figure something out for the end of the curve belts so there isn't a random gap

        // Get the correct type of belt (single, end, start, etc.)
        String beltType;

        // The belt is of the straight types and not a curve if:
        // either: both side neighbors face towards this OR both side neighbors either don't exist or don't face towards this
        if ((leftNeighborFacing && rightNeighborFacing || (leftNeighbor == null || !leftNeighborFacing) && (rightNeighbor == null || !rightNeighborFacing))) {
            // The belt is a single if:
            // no front neighbor
            // rear neighbor does not exist or does not face towards this
            if (frontNeighbor == null
                && (rearNeighbor == null || !rearNeighborFacing)) {
                beltType = "single";
            }
            // The belt is a start if:
            // has a front neighbor
            // rear does not exist or does not face towards this
            else if (frontNeighbor != null && (rearNeighbor == null || !rearNeighborFacing)) {
                beltType = "start";
            }
            // The belt is an end if: has no front neighbor and rear faces towards this
            // Second condition is always true by this point
            else if (frontNeighbor == null) {
                beltType = "end";
            }
            // The belt is a middle if: has a front and a rear that faces towards this
            // These conditions are always true by this point
            else {
                beltType = "middle";
            }
        }
        // Otherwise it is on of the curve types
        else {
            // The belt is a left curve if left facing is true
            if (leftNeighborFacing) { beltType = "left_curve"; }
            // The belt is a right curve if right facing is true
            // This should always be true by this point
            else { beltType = "right_curve"; }
        }

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
