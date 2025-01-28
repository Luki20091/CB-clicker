package com.example.cb_clicker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CharacterSelectionActivity extends AppCompatActivity {

    private Button backButton, selectCharacter1Button, selectCharacter2Button, selectCharacter3Button;
    private ImageView selectedCharacterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        // Inicjalizacja widoków
        backButton = findViewById(R.id.backButton);
        selectCharacter1Button = findViewById(R.id.selectCharacter1Button);
        selectCharacter2Button = findViewById(R.id.selectCharacter2Button);
        selectCharacter3Button = findViewById(R.id.selectCharacter3Button);
        selectedCharacterImage = findViewById(R.id.selectedCharacterImage);

        // Ustawienie domyślnej postaci
        updateCharacterImage();

        // Przycisk powrotu do głównej aktywności
        backButton.setOnClickListener(v -> finish());

        // Przycisk wyboru pierwszej postaci
        selectCharacter1Button.setOnClickListener(v -> {
            if (!MainActivity.boughtCharacters[0]) {
                Toast.makeText(CharacterSelectionActivity.this, "Ta postać nie została zakupiona!", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.selectedCharacter = 0;  // Zapisujemy wybraną postać
                // Zapisujemy wybór postaci w SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("gameData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("selectedCharacter", 0);
                editor.apply();

                MainActivity.imageView.setImageResource(R.drawable.character1);  // Zmieniamy postać na pierwszą
                Toast.makeText(CharacterSelectionActivity.this, "Wybrałeś postać 1", Toast.LENGTH_SHORT).show();
                finish();  // Powrót do głównej aktywności
            }
        });

        // Przycisk wyboru drugiej postaci
        selectCharacter2Button.setOnClickListener(v -> {
            if (!MainActivity.boughtCharacters[1]) {
                Toast.makeText(CharacterSelectionActivity.this, "Ta postać nie została zakupiona!", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.selectedCharacter = 1;  // Zapisujemy wybraną postać
                // Zapisujemy wybór postaci w SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("gameData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("selectedCharacter", 1);
                editor.apply();

                MainActivity.imageView.setImageResource(R.drawable.character2);  // Zmieniamy postać na drugą
                Toast.makeText(CharacterSelectionActivity.this, "Wybrałeś postać 2", Toast.LENGTH_SHORT).show();
                finish();  // Powrót do głównej aktywności
            }
        });

        // Przycisk wyboru trzeciej postaci (domyślna)
        selectCharacter3Button.setOnClickListener(v -> {
            MainActivity.selectedCharacter = -1;  // Brak wybranej postaci
            // Zapisujemy brak wyboru postaci w SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("gameData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("selectedCharacter", -1);
            editor.apply();

            MainActivity.imageView.setImageResource(R.drawable.default_image);  // Zmieniamy postać na domyślną
            Toast.makeText(CharacterSelectionActivity.this, "Wybrałeś postać domyślną", Toast.LENGTH_SHORT).show();
            finish();  // Powrót do głównej aktywności
        });
    }

    // Funkcja aktualizująca widok wybranej postaci
    private void updateCharacterImage() {
        // Sprawdzamy aktualnie wybraną postać i ustawiamy odpowiedni obrazek
        if (MainActivity.selectedCharacter == 0) {
            selectedCharacterImage.setImageResource(R.drawable.character1);
        } else if (MainActivity.selectedCharacter == 1) {
            selectedCharacterImage.setImageResource(R.drawable.character2);
        } else {
            selectedCharacterImage.setImageResource(R.drawable.default_image);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCharacterImage(); // Upewniamy się, że obrazek postaci jest aktualny po powrocie do tej aktywności
    }
}
