package com.example.cb_clicker;

import android.content.Intent;
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
            if (MainActivity.clickBank >= 50 && !MainActivity.boughtCharacters[0]) { // Jeśli mamy wystarczająco kliknięć w banku i postać nie została jeszcze kupiona
                MainActivity.clickBank -= 50;
                MainActivity.boughtCharacters[0] = true;
                MainActivity.imageView.setImageResource(R.drawable.character1); // Zmiana postaci w głównym oknie gry
                storeCharacterImage1.setImageResource(R.drawable.character1); // Zmiana postaci w sklepie
                Toast.makeText(StoreActivity.this, "Kupiłeś nową postać!", Toast.LENGTH_SHORT).show();

                // Aktualizujemy stan banku w MainActivity
                ((MainActivity) getParent()).updateClickBankText();
                updateClickBankText();  // Zaktualizuj bank kliknięć również w sklepie
            } else if (MainActivity.boughtCharacters[0]) {
                Toast.makeText(StoreActivity.this, "Ta postać jest już zakupiona!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(StoreActivity.this, "Za mało kliknięć w banku!", Toast.LENGTH_SHORT).show();
            }
        });

        // Przycisk zakupu drugiej postaci
        buyCharacterButton2.setOnClickListener(v -> {
            if (MainActivity.clickBank >= 100 && !MainActivity.boughtCharacters[1]) { // Jeśli mamy wystarczająco kliknięć w banku i postać nie została jeszcze kupiona
                MainActivity.clickBank -= 100;
                MainActivity.boughtCharacters[1] = true;
                MainActivity.imageView.setImageResource(R.drawable.character2); // Zmiana postaci w głównym oknie gry
                storeCharacterImage2.setImageResource(R.drawable.character2); // Zmiana postaci w sklepie
                Toast.makeText(StoreActivity.this, "Kupiłeś nową postać!", Toast.LENGTH_SHORT).show();

                // Aktualizujemy stan banku w MainActivity
                ((MainActivity) getParent()).updateClickBankText();
                updateClickBankText();  // Zaktualizuj bank kliknięć również w sklepie
            } else if (MainActivity.boughtCharacters[1]) {
                Toast.makeText(StoreActivity.this, "Ta postać jest już zakupiona!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(StoreActivity.this, "Za mało kliknięć w banku!", Toast.LENGTH_SHORT).show();
            }
        });

        // Przycisk zakupu ulepszenia
        buyUpgradeButton.setOnClickListener(v -> {
            if (MainActivity.clickBank >= 100) { // Jeśli mamy wystarczająco kliknięć w banku
                MainActivity.clickBank -= 100;
                MainActivity.increment = 2; // Ulepszamy inkrementację
                Toast.makeText(StoreActivity.this, "Kupiłeś ulepszenie klikacza!", Toast.LENGTH_SHORT).show();

                // Aktualizujemy stan banku w MainActivity
                ((MainActivity) getParent()).updateClickBankText();
                updateClickBankText();  // Zaktualizuj bank kliknięć również w sklepie
            } else {
                Toast.makeText(StoreActivity.this, "Za mało kliknięć na ulepszenie!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Aktualizacja wyświetlania stanu banku kliknięć w sklepie
    private void updateClickBankText() {
        clickBankText.setText("Bank: " + MainActivity.clickBank + "$");
    }
}
