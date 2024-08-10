package com.mpex.dodamdodam;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;
    private static final String TAG = "MainActivity";
    TextView babyName;
    LinearLayout l1, l2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        babyName = findViewById(R.id.babyName);
        ImageButton startOverlayButton = findViewById(R.id.start_overlay);
        startOverlayButton.setOnClickListener(v -> checkOverlayPermission());
        ImageButton changeBabyButton = findViewById(R.id.changeBabyButton);
        changeBabyButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SelectChildActivity.class));

            new Handler(Looper.getMainLooper()).postDelayed(() -> babyName.setText("Jason"), 1000);
        });
        l1 = findViewById(R.id.showAfter5sec);
        l2 = findViewById(R.id.invisibleAfter5sec);

        findViewById(R.id.more).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FoodHistoryActivity.class)));

    }

    private void checkOverlayPermission() {
        if (!Settings.canDrawOverlays(this)) {
            Log.d(TAG, "Requesting overlay permission");
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
        } else {
            Log.d(TAG, "Overlay permission already granted");
            showOverlay();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SYSTEM_ALERT_WINDOW_PERMISSION) {
            if (Settings.canDrawOverlays(this)) {
                Log.d(TAG, "Overlay permission granted");
                showOverlay();
            } else {
                Log.d(TAG, "Overlay permission not granted");
                Toast.makeText(this, "Overlay permission is needed to display the window", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showOverlay() {
        Log.d(TAG, "Starting OverlayService");
        startService(new Intent(MainActivity.this, OverlayService.class));
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.INVISIBLE);
        }, 5000);

    }
}