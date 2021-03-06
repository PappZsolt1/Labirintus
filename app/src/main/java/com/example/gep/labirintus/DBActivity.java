package com.example.gep.labirintus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        TextView tv = (TextView) findViewById(R.id.text);
        tv.setMovementMethod(new ScrollingMovementMethod());

        String s = getIntent().getExtras().getString("db");
        tv.setText(s);
    }
}
