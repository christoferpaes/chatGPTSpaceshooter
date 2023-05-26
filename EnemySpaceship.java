package com.example.releaseofspaceshooter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class EnemySpaceship {
    private static final float SPEED_PIXELS_PER_SECOND = 200;

    private RectF rect;
    private float yVelocity;
    private Paint paint;

    public EnemySpaceship(float startX, float startY) {
        rect = new RectF();
        rect.left = startX;
        rect.top = startY;
        rect.right = startX + 200; // Adjust the width as needed
        rect.bottom = startY + 200; // Adjust the height as needed

        yVelocity = SPEED_PIXELS_PER_SECOND;

        paint = new Paint();
        paint.setColor(Color.RED);
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

    public void update(long deltaTime) {
        float distance = SPEED_PIXELS_PER_SECOND * deltaTime / 1000;
        rect.top += distance;
        rect.bottom += distance;
    }

    public boolean isDestroyed() {
        // Check if the spaceship is destroyed based on game logic
        // Return true if it's destroyed, false otherwise
        // Add your own implementation here
        return false;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }
}
