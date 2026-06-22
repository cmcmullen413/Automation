package com.automation.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StoneTile extends Tile {

    // Variables that define what parts of the texture correspond to the actual tiles
    private static final int[][] TEX_CORNERS = {{70, 12}, {12, 44}, {131, 44}, {70, 72}};
    private static final int TEX_WIDTH = 52;
    private static final int TEX_Height = 26;

    public StoneTile(AssetManager manager, int xPos, int yPos) {
        super(manager, xPos, yPos);

        // Choose one of the tiles from the texture and set the texture region to point to it
        // TODO Make this actually select one of the different textures
        int[] texCorner = TEX_CORNERS[0];
        texRegion = new TextureRegion(texture, texCorner[0], texCorner[1], TEX_WIDTH, TEX_Height);
    }
}
