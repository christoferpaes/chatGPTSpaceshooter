import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.spaceshooter.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    // Game variables
    private boolean gameRunning;
    private int score;

    // Player spaceship
    private PlayerSpaceship playerSpaceship;

    // Enemy objects
    private List<EnemySpaceship> enemySpaceships;

    // Game view
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the game view
        gameView = findViewById(R.id.game_view);

        // Start the game when the game view is clicked
        gameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // Function to start the game
    private void startGame() {
        gameRunning = true;
        score = 0;
        playerSpaceship = new PlayerSpaceship();
        enemySpaceships = new ArrayList<>();
        // TODO: Initialize enemy spaceships and other game objects.

        // Start the game loop
        gameLoop();
    }

    // Game loop for updating game state
    private void gameLoop() {
        while (gameRunning) {
            // Update player spaceship position
            playerSpaceship.update();

            // Update enemy spaceships positions
            Iterator<EnemySpaceship> iterator = enemySpaceships.iterator();
            while (iterator.hasNext()) {
                EnemySpaceship enemy = iterator.next();
                enemy.update();

                // Check if enemy spaceship is off the screen and remove it
                if (enemy.getY() > SCREEN_HEIGHT) {
                    iterator.remove();
                }
            }

            // Check for collision between player spaceship and enemies
            checkCollisions();

            // TODO: Handle shooting mechanics
            if (/* check if player wants to shoot */) {
                playerShoot();
            }

            // Update score
            updateScore();

            // Update game screen
            gameView.update();

            // Delay for smooth gameplay
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Check for collision between player spaceship and enemies
  private void checkCollisions() {
    // Iterate over enemy spaceships
    Iterator<EnemySpaceship> iterator = enemySpaceships.iterator();
    while (iterator.hasNext()) {
        EnemySpaceship enemy = iterator.next();
        
        // Check collision between player and enemy spaceship
        if (RectF.intersects(playerSpaceship.getRect(), enemy.getRect())) {
            // Collision occurred, remove enemy spaceship
            iterator.remove();
            // TODO: Perform actions for player-enemy collision, e.g., decrease player health
        }
    }
}
   // Handle player shooting mechanics
private void playerShoot() {
    // Create a new bullet object at player spaceship position
    Bullet bullet = new Bullet(playerSpaceship.getX(), playerSpaceship.getY());
    // TODO: Add the bullet object to a list or perform other shooting logic
}

// Update the score
private void updateScore() {
    // Increase the score by a certain amount based on game events (e.g., destroying enemy spaceships)
    score += 10; // Increase the score by 10 for each enemy spaceship destroyed

    // TODO: Perform any additional score-related actions or updates
    // For example, you can update the score display on the screen or trigger events based on score thresholds.
}

    // Inner class representing the player spaceship
    private class PlayerSpaceship {
        private float x, y;
        private float speed;

        public PlayerSpaceship() {
            // Initialize player spaceship properties
            x = SCREEN_WIDTH / 2;
            y = SCREEN_HEIGHT - 200;
            speed = 10;
        }

        public void update() {
            // Update player spaceship position based on input or AI
            // TODO: Implement player spaceship movement logic
        }
    }

    // Inner class representing an enemy spaceship
    private class EnemySpaceship {
        private float x, y;
        private float speed;

        public EnemySpaceship(float x, float y) {
            // Initialize enemy spaceship properties
            this.x = x;
            this.y = y;
            speed = 5;
        }

        public void update() {
            // Update enemy spaceship position based on AI or predetermined patterns
            // TODO: Implement enemy spaceship movement logic
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }

    // Inner class representing the game view
    private class GameView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder holder;
        private Paint paint;

        public GameView(Context context) {
            super(context);
            holder = getHolder();
            holder.addCallback(this);
            paint = new Paint();
            paint.setColor(Color.WHITE);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // Start the game loop when the surface is created
            gameLoop();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // Handle changes to the surface (if any)
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // Stop the game loop when the surface is destroyed
            gameRunning = false;
        }

        public void update() {
            // Update the game view (draw game objects, etc.)
            if (holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();
                // TODO: Draw game objects on the canvas
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
