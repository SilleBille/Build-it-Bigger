package com.mkd.build.it.bigger.jokeviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeViewerActivity extends AppCompatActivity {

    TextView txtViewJoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_viewer);

        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            txtViewJoke = (TextView) findViewById(R.id.txt_view_joke);
            txtViewJoke.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}
