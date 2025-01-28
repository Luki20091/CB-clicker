package com.example.cb_clicker;

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
    public static ImageView imageView;
    private TextView clickCountText, clickBankText;
    private Button clickButton, resetButton, openStoreButton, selectCharacterButton;

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
}
