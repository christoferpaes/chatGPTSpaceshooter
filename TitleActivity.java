// TitleActivity.java
public class TitleActivity extends AppCompatActivity {

    private Button btnStartGame;
    private Button btnHighScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        btnStartGame = findViewById(R.id.btnStartGame);
        btnHighScores = findViewById(R.id.btnHighScores);

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        btnHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHighScores();
            }
        });
    }

    private void startGame() {
        // Start the game
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showHighScores() {
        // Show the high scores activity
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }
}
