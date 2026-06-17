package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BlankTile extends Tile{

    public BlankTile(int xPos, int yPos) {
        // TODO: Make this use an asset manager to reduce duplicate textures
        super(new Texture(Gdx.files.internal("blank_tile.png")), xPos, yPos);
    }
}
