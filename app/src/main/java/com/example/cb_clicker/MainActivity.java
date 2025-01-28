package com.example.cb_clicker;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static int clickCount = 0;
    public static int increment = 1;
    public static int clickBank = 0;  // Bank kliknięć
    public static boolean[] boughtCharacters = {false, false, false};  // Tablica śledząca zakupione postacie
    public static int selectedCharacter = -1;  // Indeks wybranej postaci, -1 oznacza brak wyboru
    public static ImageView imageView;
    private TextView clickCountText, clickBankText;
    private Button clickButton, resetButton, openStoreButton, selectCharacterButton;

    @Override
    protected void onResume() {
        super.onResume();
        loadData();  // Załaduj dane (w tym wybraną postać)
        updateClickBankText();
        updateSelectedCharacter();  // Wyświetlanie wybranej postaci
    }

    private void updateSelectedCharacter() {
        // Wyświetlamy postać, która została wybrana
        switch (selectedCharacter) {
            case 0:
                imageView.setImageResource(R.drawable.character1);
                break;
            case 1:
                imageView.setImageResource(R.drawable.character2);
                break;
            default:
                imageView.setImageResource(R.drawable.default_image);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();  // Zapisujemy dane, gdy użytkownik opuści aktywność
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickCountText = findViewById(R.id.clickCountText);
        clickBankText = findViewById(R.id.clickBankText);
        imageView = findViewById(R.id.imageView);
        clickButton = findViewById(R.id.clickButton);
        resetButton = findViewById(R.id.resetButton);
        openStoreButton = findViewById(R.id.openStoreButton);
        selectCharacterButton = findViewById(R.id.selectCharacterButton);

        // Przyciski akcji
        clickButton.setOnClickListener(v -> {
            clickCount += increment;
            clickBank += increment;  // Zwiększamy bank kliknięć
            updateUI();
        });

        resetButton.setOnClickListener(v -> {
            clickCount = 0;
            updateUI();
        });

        openStoreButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StoreActivity.class);
            startActivity(intent);
        });

        selectCharacterButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CharacterSelectionActivity.class);
            startActivity(intent);
        });
    }

    // Aktualizacja UI
    private void updateUI() {
        clickCountText.setText("Licznik: " + clickCount);
        updateClickBankText();  // Wywołanie metody aktualizującej bank kliknięć
    }

    // Publiczna metoda, by umożliwić dostęp do aktualizacji banku kliknięć
    public void updateClickBankText() {
        clickBankText.setText("Bank: " + clickBank + "$");
    }

    // Zapisanie danych do SharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("gameData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("clickBank", clickBank);
        editor.putInt("clickCount", clickCount);
        editor.putBoolean("boughtCharacter1", boughtCharacters[0]);
        editor.putBoolean("boughtCharacter2", boughtCharacters[1]);
        editor.putInt("increment", increment);
        editor.putInt("selectedCharacter", selectedCharacter);  // Zapisujemy wybraną postać
        editor.apply();
    }

    // Załadowanie danych z SharedPreferences
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("gameData", MODE_PRIVATE);
        clickBank = sharedPreferences.getInt("clickBank", 0);
        clickCount = sharedPreferences.getInt("clickCount", 0);
        boughtCharacters[0] = sharedPreferences.getBoolean("boughtCharacter1", false);
        boughtCharacters[1] = sharedPreferences.getBoolean("boughtCharacter2", false);
        increment = sharedPreferences.getInt("increment", 1);
        selectedCharacter = sharedPreferences.getInt("selectedCharacter", -1);  // Ładujemy wybraną postać
    }
}
