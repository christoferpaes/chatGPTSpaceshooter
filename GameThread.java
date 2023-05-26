package com.example.releaseofspaceshooter;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    private static final int MAX_FPS = 30;
    private static final int FRAME_DURATION = 1000 / MAX_FPS;

    private GameView gameView;
    private boolean running;

    public GameThread(SurfaceHolder holder, GameView gameView) {
        this.gameView = gameView;
        this.running = false;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        long sleepTime;

        while (running) {
            startTime = System.currentTimeMillis();

            gameView.update();
            Canvas canvas = gameView.getHolder().lockCanvas();
            if (canvas != null) {
                gameView.draw(canvas);
                gameView.getHolder().unlockCanvasAndPost(canvas);
            }

            sleepTime = FRAME_DURATION - (System.currentTimeMillis() - startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
