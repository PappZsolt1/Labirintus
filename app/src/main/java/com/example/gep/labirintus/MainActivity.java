package com.example.gep.labirintus;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        final DBHandler dbHandler = new DBHandler(this);

        Button top10Button = (Button) findViewById(R.id.top10);

        top10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recordList = "";
                int pos = 1;

                Cursor cursor = dbHandler.loadRecords();
                while(!cursor.isAfterLast()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int time = cursor.getInt(cursor.getColumnIndex("time"));
                    int tries = cursor.getInt(cursor.getColumnIndex("tries"));
                    recordList += pos + ". helyezett neve: " + name + "\nideje: " + timeConverter(time) +
                            "\npróbálkozásainak száma: " + tries + "\n\n";
                    pos++;
                    cursor.moveToNext();
                }

                Intent dbIntent = new Intent(MainActivity.this, DBActivity.class);
                dbIntent.putExtra("db", recordList);
                startActivity(dbIntent);
            }
        });

        Button playButton = (Button) findViewById(R.id.play);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);

                startActivity(gameIntent);
            }
        });

        //Log.d("asd", timeConverter(65*1000 + 432));
    }

    public String timeConverter(int ms) {
        String time = "";
        int afterDot = ms % 1000;
        int sec = (ms / 1000) % 60;
        int min = ms / (60 * 1000);
        String sec2 = "";
        if (sec == 0) {
            sec2 += "00";
        }
        else if (sec < 10) {
            sec2 += "0" + sec;
        }
        else {
            sec2 += sec;
        }
        time += min + ":" + sec2 + "." + afterDot;
        return time;
    }
}
