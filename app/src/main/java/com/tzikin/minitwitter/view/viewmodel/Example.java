package com.tzikin.minitwitter.view.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tzikin.minitwitter.R;
import com.tzikin.minitwitter.view.viewmodel.ui.main.ExampleFragment;

public class Example extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ExampleFragment.newInstance())
                    .commitNow();
        }
    }
}