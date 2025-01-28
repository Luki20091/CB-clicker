package com.example.cb_clicker;

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
            if (MainActivity.boughtCharacters[0]) {
                Toast.makeText(CharacterSelectionActivity.this, "Ta postać już została zakupiona!", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.imageView.setImageResource(R.drawable.character1);  // Zmieniamy postać na pierwszą
                MainActivity.boughtCharacters[0] = true;  // Zaznaczamy, że postać została zakupiona
                Toast.makeText(CharacterSelectionActivity.this, "Wybrałeś postać 1", Toast.LENGTH_SHORT).show();
                finish();  // Powrót do głównej aktywności
            }
        });

        // Przycisk wyboru drugiej postaci
        selectCharacter2Button.setOnClickListener(v -> {
            if (MainActivity.boughtCharacters[1]) {
                Toast.makeText(CharacterSelectionActivity.this, "Ta postać już została zakupiona!", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.imageView.setImageResource(R.drawable.character2);  // Zmieniamy postać na drugą
                MainActivity.boughtCharacters[1] = true;  // Zaznaczamy, że postać została zakupiona
                Toast.makeText(CharacterSelectionActivity.this, "Wybrałeś postać 2", Toast.LENGTH_SHORT).show();
                finish();  // Powrót do głównej aktywności
            }
        });

        // Przycisk wyboru trzeciej postaci (domyślna)
        selectCharacter3Button.setOnClickListener(v -> {
            if (MainActivity.boughtCharacters[2]) {
                Toast.makeText(CharacterSelectionActivity.this, "Ta postać już została zakupiona!", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.imageView.setImageResource(R.drawable.default_image);  // Zmieniamy postać na domyślną
                MainActivity.boughtCharacters[2] = true;  // Zaznaczamy, że postać została zakupiona
                Toast.makeText(CharacterSelectionActivity.this, "Wybrałeś postać domyślną", Toast.LENGTH_SHORT).show();
                finish();  // Powrót do głównej aktywności
            }
        });
    }

    // Funkcja aktualizująca widok wybranej postaci
    private void updateCharacterImage() {
        if (MainActivity.boughtCharacters[0]) {
            selectedCharacterImage.setImageResource(R.drawable.character1);
        } else if (MainActivity.boughtCharacters[1]) {
            selectedCharacterImage.setImageResource(R.drawable.character2);
        } else {
            selectedCharacterImage.setImageResource(R.drawable.default_image);
        }
    }
}
