package com.instrumentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        Intent intent = getIntent();
        String message = intent.getStringExtra("MESSAGE");

        // Show message.
        ((TextView)findViewById(R.id.show_text_view)).setText(message);
    }

}
