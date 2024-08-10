package com.mpex.dodamdodam;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectChildActivity extends AppCompatActivity {

    LinearLayout layout1, layout2;
    TextView day1, day2, name1, name2, dayValue1, dayValue2, nameValue1, nameValue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_child);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(v -> finish());

        layout1 = findViewById(R.id.firstLayout);
        layout2 = findViewById(R.id.secondLayout);
        day1 = findViewById(R.id.firstDay);
        day2 = findViewById(R.id.secondDay);
        dayValue1 = findViewById(R.id.firstDayValue);
        dayValue2 = findViewById(R.id.secondDayValue);
        name1 = findViewById(R.id.firstBirthName);
        name2 = findViewById(R.id.secondBirthName);
        nameValue1 = findViewById(R.id.firstBirthNameValue);
        nameValue2 = findViewById(R.id.secondBirthNameValue);

        layout2.setOnClickListener(v -> changeColorOfRadio());
        layout1.setOnClickListener(v -> changeColorOfRadio());
    }

    void changeColorOfRadio() {
        // Get the current background of layout1 and layout2
        Drawable background1 = layout1.getBackground();
        Drawable background2 = layout2.getBackground();

        // Get the current backgroundTint of layout1 and layout2
        ColorStateList backgroundTint1 = layout1.getBackgroundTintList();
        ColorStateList backgroundTint2 = layout2.getBackgroundTintList();

        // Swap the backgrounds
        layout1.setBackground(background2);
        layout2.setBackground(background1);

        // Swap the backgroundTint
        layout1.setBackgroundTintList(backgroundTint2);
        layout2.setBackgroundTintList(backgroundTint1);

        // Swap the text colors for name and day TextViews
        int colorName1 = name1.getCurrentTextColor();
        int colorName2 = name2.getCurrentTextColor();
        int colorDay1 = day1.getCurrentTextColor();
        int colorDay2 = day2.getCurrentTextColor();

        name1.setTextColor(colorName2);
        name2.setTextColor(colorName1);
        day1.setTextColor(colorDay2);
        day2.setTextColor(colorDay1);

        // Swap the text colors for nameValue and dayValue TextViews
        int colorNameValue1 = nameValue1.getCurrentTextColor();
        int colorNameValue2 = nameValue2.getCurrentTextColor();
        int colorDayValue1 = dayValue1.getCurrentTextColor();
        int colorDayValue2 = dayValue2.getCurrentTextColor();

        nameValue1.setTextColor(colorNameValue2);
        nameValue2.setTextColor(colorNameValue1);
        dayValue1.setTextColor(colorDayValue2);
        dayValue2.setTextColor(colorDayValue1);
    }


}