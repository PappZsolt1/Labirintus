package com.example.gep.labirintus;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button helpButton = (Button) findViewById(R.id.help);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setTitle("A játék célja:");
                ab.setMessage("A kék golyót a mobil döntésével a labirintuson át a zöld mezőre kell juttatni. " +
                        "Ha a golyó az egyik piros lyukba beleesik, az visszakerül a kezdőhelyére. " +
                        "A pálya teljesítése után az eltelt idő és a próbálkozások száma rögzítésre kerül. " +
                        "Légy te a legügyesebb!");
                ab.setPositiveButton("Értettem!", null);
                ab.show();
            }
        });



    }
}
