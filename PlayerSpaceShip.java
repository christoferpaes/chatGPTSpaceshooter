package com.example.releaseofspaceshooter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class PlayerSpaceship {
    private static final float SPEED_PIXELS_PER_SECOND = 400;

    private RectF rect;
    private float xVelocity;
    private Paint paint;

    public PlayerSpaceship(float startX, float startY) {
        rect = new RectF();
        rect.left = startX;
        rect.top = startY;
        rect.right = startX + 200; // Adjust the width as needed
        rect.bottom = startY + 200; // Adjust the height as needed

        xVelocity = 0;

        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    public RectF getRect() {
        return rect;
    }

    public RectF getHitbox() {
        // Adjust the hitbox if needed, relative to the spaceship's rect
        // For example, if you want a smaller hitbox, you can reduce the size
        // or change the coordinates to fit the desired hitbox dimensions.
        RectF hitbox = new RectF(rect);
        return hitbox;
    }

    public void update() {
        rect.left += xVelocity / 60; // 60 FPS assumption
        rect.right = rect.left + 200; // Adjust the width as needed
    }

    public void setMovementState(int state) {
        if (state == 0) {
            // Stop moving
            xVelocity = 0;
        } else if (state == 1) {
            // Move left
            xVelocity = -SPEED_PIXELS_PER_SECOND;
        } else if (state == 2) {
            // Move right
            xVelocity = SPEED_PIXELS_PER_SECOND;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    public float getX() {
        return rect.left;
    }

    public float getY() {
        return rect.top;
    }
}
