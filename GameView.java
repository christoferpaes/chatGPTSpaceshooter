package com.example.releaseofspaceshooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameThread = new GameThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (gameThread.getState() == Thread.State.NEW) {
            gameThread.start();
        } else if (gameThread.getState() == Thread.State.TERMINATED) {
            gameThread = new GameThread(getHolder(), this);
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Handle surface changes, if needed
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK); // Set the background color

        // Draw game objects here
        // For example, you can call the draw methods of player spaceship, enemy spaceships, bullets, etc.
    }

    public void update() {
        // Update game state here
        // For example, you can update the positions of player spaceship, enemy spaceships, bullets, etc.
        // You can also handle game logic and collisions in this method
        // Once the game state is updated, call invalidate() to trigger the redraw of the view
        invalidate();
    }
}
