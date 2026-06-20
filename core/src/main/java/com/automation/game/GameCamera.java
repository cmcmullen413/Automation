package com.automation.game;

import com.badlogic.gdx.Input;

public class GameCamera {
    private static final float CAM_SPEED = 250f;
    private static final float ZOOM_SPEED = 1f;

    private float x,y;
    private float zoom;

    public GameCamera() {
        x = 0;
        y = 0;
        zoom = 1;
    }

    // Public getters
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZoom() { return zoom; }

    public void update(float delta) {
        // Move the camera based on the WASD or arrow key inputs
        x += delta* ((InputHandler.keyStates.get(Input.Keys.D) || InputHandler.keyStates.get(Input.Keys.RIGHT) ? CAM_SPEED : 0)
            - (InputHandler.keyStates.get(Input.Keys.A) || InputHandler.keyStates.get(Input.Keys.LEFT) ? CAM_SPEED : 0));
        y += delta * ((InputHandler.keyStates.get(Input.Keys.W) || InputHandler.keyStates.get(Input.Keys.UP) ? CAM_SPEED : 0)
            - (InputHandler.keyStates.get(Input.Keys.S) || InputHandler.keyStates.get(Input.Keys.DOWN) ? CAM_SPEED : 0));

        // Zoom the camera with the scroll wheel
        // TODO
    }
}
