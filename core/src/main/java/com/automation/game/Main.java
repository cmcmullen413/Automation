package com.automation.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Main extends ApplicationAdapter {

    // Number of frames in the assembler sprite sheet
    private static final int FRAMES = 10;
    // The amount of time spent on each frame of the animation
    private static final float FRAME_INTERVAL = 0.1f;

    // Objects
    public SpriteBatch spriteBatch;

    // Variable for tracking elapsed time
    float deltaTime;

    @Override
    public void create() {
        // Load the sprite sheet
        assemblerSheet = new Texture(Gdx.files.internal("factory/assembler/assembler-1/assembler-0001.png"));

        // Split the texture into the discrete frames
        // Since the texture is only 1 row of frames, we can just grab the first array from the split
        TextureRegion[] assemblerFrames = TextureRegion.split(assemblerSheet, assemblerSheet.getWidth()/FRAMES, assemblerSheet.getHeight())[0];

        // Init the animation with the frame interval and array of frames
        assemblerAnimation = new Animation<>(FRAME_INTERVAL, assemblerFrames);

        // Instantiate a sprite batch and reset the state time
        spriteBatch = new SpriteBatch();
        deltaTime = 0f;
    }

    @Override
    public void render() {
        // Clear the background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Update the deltaTime
        deltaTime += Gdx.graphics.getDeltaTime();

        // Get the current frame of the animation
        TextureRegion currentFrame = assemblerAnimation.getKeyFrame(deltaTime, true);

        // Begin the sprite batch to make draw calls
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        // Free sprite batch and textures
        spriteBatch.dispose();
        assemblerSheet.dispose();
    }
}
