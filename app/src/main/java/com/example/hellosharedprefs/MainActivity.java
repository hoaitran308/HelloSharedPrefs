package com.example.hellosharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String HELLO_SHARED_PREFS = "HELLO_SHARED_PREFS";
    private static final String CURRENT_COLOR_KEY = "CURRENT_COLOR_KEY";
    private static final String BLACK_KEY = "BLACK_KEY";
    private static final String RED_KEY = "RED_KEY";
    private static final String BLUE_KEY = "BLUE_KEY";
    private static final String GREEN_KEY = "GREEN_KEY";
    private SharedPreferences sharedPref;
    private TextView number;
    private String currentColorKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onInit();
    }

    private void onInit() {
        sharedPref = getSharedPreferences(HELLO_SHARED_PREFS, MODE_PRIVATE);

        number = findViewById(R.id.number);

        currentColorKey = sharedPref.getString(CURRENT_COLOR_KEY, HELLO_SHARED_PREFS);
        onBtnColorClick(currentColorKey);

        TextView btnBlack = findViewById(R.id.btnBlack);
        TextView btnRed = findViewById(R.id.btnRed);
        TextView btnBlue = findViewById(R.id.btnBlue);
        TextView btnGreen = findViewById(R.id.btnGreen);

        btnBlack.setOnClickListener(view -> onBtnColorClick(BLACK_KEY));
        btnRed.setOnClickListener(view -> onBtnColorClick(RED_KEY));
        btnBlue.setOnClickListener(view -> onBtnColorClick(BLUE_KEY));
        btnGreen.setOnClickListener(view -> onBtnColorClick(GREEN_KEY));

        TextView btnCount = findViewById(R.id.btnCount);
        btnCount.setOnClickListener(view -> onBtnCountClick());

        TextView btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(view -> onBtnClearClick());
    }

    private void onBtnClearClick() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        currentColorKey = HELLO_SHARED_PREFS;
        number.setText(getResources().getString(R.string.clear_notify));
        number.setBackgroundColor(getColorId(currentColorKey));
    }

    private void onBtnCountClick() {
        if (currentColorKey.equals(HELLO_SHARED_PREFS)) {
            return;
        }

        int newValue = Integer.parseInt(number.getText().toString()) + 1;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(currentColorKey, newValue);
        editor.apply();

        number.setText(String.valueOf(newValue));
    }

    private void onBtnColorClick(String COLOR_KEY) {
        currentColorKey = COLOR_KEY;
        int numberInSharedPref = sharedPref.getInt(COLOR_KEY, 0);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(CURRENT_COLOR_KEY, COLOR_KEY);
        editor.apply();

        number.setText(String.valueOf(numberInSharedPref));
        number.setBackgroundColor(getColorId(COLOR_KEY));

    }

    private int getColorId(String COLOR_KEY) {
        switch (COLOR_KEY) {
            case BLACK_KEY: return getResources().getColor(R.color.black);
            case RED_KEY: return getResources().getColor(R.color.red);
            case BLUE_KEY: return getResources().getColor(R.color.blue);
            case GREEN_KEY: return getResources().getColor(R.color.green);
            default: return getResources().getColor(R.color.white);
        }
    }
}