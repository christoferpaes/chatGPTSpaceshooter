package com.example.releaseofspaceshooter;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.releaseofspaceshooter.databinding.ActivityHighScoresBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// HighScoresActivity.java
public class HighScoresActivity extends AppCompatActivity {

    private ListView listViewHighScores;
    private List<String> highScoresList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        listViewHighScores = findViewById(R.id.listViewHighScores);

        // Load the high scores from storage or database
        loadHighScores();

        // Set up the adapter for the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highScoresList);
        listViewHighScores.setAdapter(adapter);
    }

    private void loadHighScores() {
        // Retrieve the high scores from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("HighScores", MODE_PRIVATE);
        Set<String> highScoresSet = sharedPreferences.getStringSet("scores", new HashSet<>());

        // Convert the Set to a List for easier manipulation
        highScoresList = new ArrayList<>(highScoresSet);

        // Sort the high scores in descending order
        Collections.sort(highScoresList, new Comparator<String>() {
            @Override
            public int compare(String score1, String score2) {
                // Assuming the high scores are stored as strings in the format "Score: X"
                int scoreValue1 = Integer.parseInt(score1.split(": ")[1]);
                int scoreValue2 = Integer.parseInt(score2.split(": ")[1]);
                return scoreValue2 - scoreValue1;
            }
        });
    }
}
