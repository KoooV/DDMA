package com.example.ddma_activityresapi2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final int fragmentContainerId = R.id.fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация FragmentC в ContainerView
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, new FragmentC())
                    .commit();
        }
    }

    public void navigateToFragmentB() {
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainerId, new FragmentB())
                .addToBackStack(null)
                .commit();
    }

    public void navigateToFragmentC() {
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainerId, new FragmentC())
                .addToBackStack(null)
                .commit();
    }
    }
