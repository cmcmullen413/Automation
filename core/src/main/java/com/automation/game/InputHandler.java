package com.automation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;

public class InputHandler extends InputAdapter {

    // The keys that are tracked
    private static final int[] keys = {
        // Camera movement
        Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D,
        Input.Keys.UP, Input.Keys.LEFT, Input.Keys.DOWN, Input.Keys.RIGHT
    };

    // Position of the mouse
    // Stores a reference to the Camera object to unproject the coordinates
    private static Camera projCamera;
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
     * Passes in the projection camera used so the mouse coordinates can be interpreted
     */
    public static void setProjectionCamera(Camera camera) {
        projCamera = camera;
    }

    /**
     * Updates all the inputs tracked
     * This includes any keys in the keymap as well as mouse position and clicks
     */
    public static void update() {
        // Update mouse
        Vector3 mousePos = projCamera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        mouseX = mousePos.x;
        mouseY = mousePos.y;
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

    private final GameCamera camera;

    public InputHandler(GameCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        camera.zoom(amountY);
        return true;
    }


}
