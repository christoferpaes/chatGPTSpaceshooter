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

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Initialize the game view
        gameView = new GameView(this);
        setContentView(gameView);

        // Start the game
        startGame();
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

    // Function to handle player shooting
    private void playerShoot() {
        Projectile projectile = new Projectile(playerSpaceship.getX(), playerSpaceship.getY());
        playerSpaceship.getProjectiles().add(projectile);
    }

    // Function to check collisions between player spaceship and enemies
    private void checkCollisions() {
        // Check collision between player spaceship and each enemy
        for (EnemySpaceship enemy : enemySpaceships) {
            if (playerSpaceship.collidesWith(enemy)) {
                // Collision occurred, handle game over or decrease player health, etc.
                gameOver();
                return;
            }
        }

        // Check collision between player projectiles and enemies
        Iterator<Projectile> iterator = playerSpaceship.getProjectiles().iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            for (EnemySpaceship enemy : enemySpaceships) {
                if (projectile.collidesWith(enemy)) {
                    // Collision occurred, remove enemy and projectile, and increase score
                    enemySpaceships.remove(enemy);
                    iterator.remove();
                    increaseScore();
                    break;
                }
            }
        }
    }

    // Function to increase the score
    private void increaseScore() {
        score += 10; // Increase the score by a certain amount
        // Update the score display on the screen
        // ...
    }

    // Function to update the game screen
    private void updateGameScreen() {
        // TODO: Update the game screen with the current positions of player spaceship, enemies, and projectiles
        // ...
    }

    // Function to handle game over
    private void gameOver() {
        gameRunning = false;
        // TODO: Handle game over logic, such as showing a game over message, saving the high score, etc.
        // ...
    }

    // Inner class representing the enemy spaceship
    private class EnemySpaceship {
        private int x;
        private int y;

        public EnemySpaceship(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void update() {
            // TODO: Update the enemy spaceship's position
            // ...
        }
    }

    // Inner class representing the player spaceship
    private class PlayerSpaceship {
        private int x;
        private int y;
        private List<Projectile> projectiles;

        public PlayerSpaceship() {
            this.x = SCREEN_WIDTH / 2;
            this.y = SCREEN_HEIGHT - 100; // Adjust the initial position as needed
            this.projectiles = new ArrayList<>();
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public List<Projectile> getProjectiles() {
            return projectiles;
        }

        public void update() {
            // TODO: Update the player spaceship's position
            // ...
        }

        public boolean collidesWith(EnemySpaceship enemy) {
            // TODO: Implement collision detection logic between player spaceship and enemy spaceship
            // ...
            return false;
        }
    }

    // Inner class representing the projectiles fired by the player spaceship
    private class Projectile {
        private int x;
        private int y;

        public Projectile(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void update() {
            // TODO: Update the projectile's position
            // ...
        }

        public boolean collidesWith(EnemySpaceship enemy) {
            // TODO: Implement collision detection logic between projectile and enemy spaceship
            // ...
            return false;
        }
    }

    // Game view to handle drawing and rendering of game objects
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
            // Handle surface changes, if needed
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // Stop the game loop and perform cleanup when the surface is destroyed
            gameRunning = false;
            // Perform any necessary cleanup here
        }

        public void update() {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                // Clear the canvas
                canvas.drawColor(Color.BLACK);

                // Draw player spaceship
                canvas.drawCircle(playerSpaceship.getX(), playerSpaceship.getY(), 20, paint);

                // Draw enemy spaceships
                for (EnemySpaceship enemy : enemySpaceships) {
                    canvas.drawCircle(enemy.getX(), enemy.getY(), 20, paint);
                }

                // Draw projectiles
                for (Projectile projectile : playerSpaceship.getProjectiles()) {
                    canvas.drawCircle(projectile.getX(), projectile.getY(), 10, paint);
                }

                // TODO: Draw other game objects as needed

                // Draw score
                paint.setTextSize(30);
                canvas.drawText("Score: " + score, 10, 50, paint);

                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
