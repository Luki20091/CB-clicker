package com.example.cb_clicker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StoreActivity extends AppCompatActivity {

    private Button backToMainButton, buyCharacterButton1, buyCharacterButton2, buyUpgradeButton;
    private ImageView storeCharacterImage1, storeCharacterImage2;
    private TextView clickBankText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // Inicjalizacja widoków
        backToMainButton = findViewById(R.id.backToMainButton);
        buyCharacterButton1 = findViewById(R.id.buyCharacterStoreButton1);
        buyCharacterButton2 = findViewById(R.id.buyCharacterStoreButton2);
        buyUpgradeButton = findViewById(R.id.buyUpgradeStoreButton);
        storeCharacterImage1 = findViewById(R.id.storeCharacterImage1);
        storeCharacterImage2 = findViewById(R.id.storeCharacterImage2);
        clickBankText = findViewById(R.id.clickBankText); // Wyświetlanie banku w sklepie

        // Zaktualizowanie stanu banku kliknięć na początku
        updateClickBankText();

        // Przycisk powrotu do głównej aktywności
        backToMainButton.setOnClickListener(v -> finish());

        // Przycisk zakupu pierwszej postaci
        buyCharacterButton1.setOnClickListener(v -> {
            if (MainActivity.clickBank >= 50 && !MainActivity.boughtCharacters[0]) {
                MainActivity.clickBank -= 50;
                MainActivity.boughtCharacters[0] = true;
                MainActivity.imageView.setImageResource(R.drawable.character1);
                storeCharacterImage1.setImageResource(R.drawable.character1);
                Toast.makeText(StoreActivity.this, "Kupiłeś nową postać!", Toast.LENGTH_SHORT).show();
                saveGameData();
                updateClickBankText();
                finish();  // Powrót do głównej aktywności
            } else if (MainActivity.boughtCharacters[0]) {
                Toast.makeText(StoreActivity.this, "Ta postać jest już zakupiona!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(StoreActivity.this, "Za mało kliknięć w banku!", Toast.LENGTH_SHORT).show();
            }
        });

        // Przycisk zakupu drugiej postaci
        buyCharacterButton2.setOnClickListener(v -> {
            if (MainActivity.clickBank >= 100 && !MainActivity.boughtCharacters[1]) {
                MainActivity.clickBank -= 100;
                MainActivity.boughtCharacters[1] = true;
                MainActivity.imageView.setImageResource(R.drawable.character2);
                storeCharacterImage2.setImageResource(R.drawable.character2);
                Toast.makeText(StoreActivity.this, "Kupiłeś nową postać!", Toast.LENGTH_SHORT).show();
                saveGameData();
                updateClickBankText();
                finish();  // Powrót do głównej aktywności

            } else if (MainActivity.boughtCharacters[1]) {
                Toast.makeText(StoreActivity.this, "Ta postać jest już zakupiona!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(StoreActivity.this, "Za mało kliknięć w banku!", Toast.LENGTH_SHORT).show();
            }
        });

        // Przycisk zakupu ulepszenia
        buyUpgradeButton.setOnClickListener(v -> {
            if (MainActivity.clickBank >= 100) {
                MainActivity.clickBank -= 100;
                MainActivity.increment = 2;
                Toast.makeText(StoreActivity.this, "Kupiłeś ulepszenie klikacza!", Toast.LENGTH_SHORT).show();
                saveGameData();
                updateClickBankText();
                finish();  // Powrót do głównej aktywności
            } else {
                Toast.makeText(StoreActivity.this, "Za mało kliknięć na ulepszenie!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Zapisanie stanu gry do SharedPreferences
    private void saveGameData() {
        SharedPreferences sharedPreferences = getSharedPreferences("gameData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("clickBank", MainActivity.clickBank);
        editor.putBoolean("boughtCharacter1", MainActivity.boughtCharacters[0]);
        editor.putBoolean("boughtCharacter2", MainActivity.boughtCharacters[1]);
        editor.putInt("increment", MainActivity.increment);
        editor.apply();
    }

    // Aktualizacja wyświetlania stanu banku kliknięć w sklepie
    private void updateClickBankText() {
        clickBankText.setText("Bank: " + MainActivity.clickBank + "$");
    }
}
