package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import java.util.HashMap;

public class InputHandler extends InputAdapter {

    // The keys that are tracked
    private static final int[] keys = {
        // Camera movement
        Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D,
        Input.Keys.UP, Input.Keys.LEFT, Input.Keys.DOWN, Input.Keys.RIGHT
    };

    // Position of the mouse
    public static float mouseX;
    public static float mouseY;
    // Holds whether the buttons were just pressed
    public static boolean leftClick;
    public static boolean rightClick;
    // Holds whether the buttons are currently held
    public static boolean leftHold;
    public static boolean rightHold;

    // Holds whether the keys were just pressed
    public static HashMap<Integer, Boolean> keyPresses = new HashMap<>();
    // The current state of the key presses
    public static HashMap<Integer, Boolean> keyStates = new HashMap<>();

    /**
     * Updates all the inputs tracked
     * This includes any keys in the keymap as well as mouse position and clicks
     */
    public static void update() {
        // Update mouse
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();
        leftClick = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
        rightClick = Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT);
        leftHold = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        rightHold = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);

        // Update keys
        for (int key : keys) {
            keyPresses.put(key, Gdx.input.isKeyJustPressed(key));
            keyStates.put(key, Gdx.input.isKeyPressed(key));
        }
    }

    // Non-static part of the input handler
    // Currently just used for camera scrolling
    // This is because it can only be done with events

    private GameCamera camera;

    public InputHandler(GameCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        camera.zoom(amountY);
        return true;
    }


}
