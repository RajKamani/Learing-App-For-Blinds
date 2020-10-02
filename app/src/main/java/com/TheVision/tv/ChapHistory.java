package com.TheVision.tv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.util.Locale;

public class ChapHistory extends AppCompatActivity {

    Button btn_1, btn_2, btn_3, btn_4, btn_5;
    TextToSpeech t1;
    Bundle b1;
    String tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap_history);

        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        b1 = new Bundle();

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                    t1.speak("click for subject", TextToSpeech.QUEUE_FLUSH, null, null);

                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);
        tp = sharedPreferences.getString("lang", "");

        btn_1.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btn_1.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null);
                Intent i1 = new Intent(ChapHistory.this, displayText.class);
                if (tp.equals("Hindi")) {
                    b1.putString("file", "6hin.txt");
                } else {
                    b1.putString("file", "6.txt");
                }
                i1.putExtras(b1);
                startActivity(i1);

            }
        }));

        btn_2.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btn_2.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null);
                Intent i1 = new Intent(ChapHistory.this, displayText.class);
                if (tp.equals("Hindi")) {
                    b1.putString("file", "7hin.txt");
                } else {
                    b1.putString("file", "7.txt");
                }

                i1.putExtras(b1);
                startActivity(i1);
            }
        }));

        btn_3.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btn_3.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null);
                Intent i1 = new Intent(ChapHistory.this, displayText.class);
                if (tp.equals("Hindi")) {
                    b1.putString("file", "8hin.txt");
                } else {
                    b1.putString("file", "8.txt");
                }

                i1.putExtras(b1);
                startActivity(i1);
            }
        }));

        btn_4.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btn_4.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null);
                Intent i1 = new Intent(ChapHistory.this, displayText.class);
                if (tp.equals("Hindi")) {
                    b1.putString("file", "9hin.txt");
                } else {
                    b1.putString("file", "9.txt");
                }

                i1.putExtras(b1);
                startActivity(i1);
            }
        }));
        btn_5.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btn_5.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null);
                Intent i1 = new Intent(ChapHistory.this, displayText.class);
                if (tp.equals("Hindi")) {
                    b1.putString("file", "10hin.txt");
                } else {
                    b1.putString("file", "10.txt");
                }

                i1.putExtras(b1);
                startActivity(i1);
            }
        }));
    }
}
