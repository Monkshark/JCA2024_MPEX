package com.mpex.dodamdodam;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FoodHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.back).setOnClickListener(v -> finish());

        TextView selectButtonText = findViewById(R.id.selectButtonText);
        LinearLayout redButton, greenButton;
        redButton = findViewById(R.id.redButton);
        greenButton = findViewById(R.id.greenButton);

        TextView redButtonText, greenButtonText;
        redButtonText = findViewById(R.id.redButtonText);
        greenButtonText = findViewById(R.id.greenButtonText);

        selectButtonText.setOnClickListener(v -> {
            redButton.setVisibility(View.VISIBLE);
            greenButton.setVisibility(View.VISIBLE);
            selectButtonText.setText("cancel");
        });

        findViewById(R.id.myItems).setOnClickListener(v -> {
            findViewById(R.id.cartItems).setBackground(null);
            findViewById(R.id.myItems).setBackgroundResource(R.drawable.roundbox_green_8dp);
            findViewById(R.id.myItems).setBackgroundTintList(findViewById(R.id.cartItems).getBackgroundTintList());
            findViewById(R.id.cartItems).setBackgroundTintList(findViewById(R.id.myItems).getBackgroundTintList());
//            findViewById(R.id.myItemsText).
        });
//        greenButton.setOnClickListener(v -> );
    }
}