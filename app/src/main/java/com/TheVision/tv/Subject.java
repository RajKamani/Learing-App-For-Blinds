package com.TheVision.tv;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.util.ArrayList;
import java.util.Locale;

public class Subject extends AppCompatActivity {

    Button btnSubEnglish, btnSubMath, btnSubHis, btnSubGk, btnSubInternet;
    TextToSpeech t1;
    String tts, tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjects);

        SharedPreferences sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);
        tp = sharedPreferences.getString("lang", "");

        btnSubEnglish = (Button) findViewById(R.id.btnSubEng);
        btnSubMath = (Button) findViewById(R.id.btnSubMath);
        btnSubHis = (Button) findViewById(R.id.btnSubHis);
        btnSubGk = (Button) findViewById(R.id.btnSubGK);
        btnSubInternet = (Button) findViewById(R.id.btnSubInternet);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                    t1.setSpeechRate(0.9f);
                    t1.setPitch(1.0f);
                    if (displayText.Flag_bookmark == 1) {

                    } else {
                        tts = "click any where single click for subject name and double click for select subject";
                    }
                    t1.speak(tts, TextToSpeech.QUEUE_FLUSH, null, null);

                }
            }
        });

        btnSubEnglish.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {

                t1.speak(btnSubEnglish.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null, null);
                Intent intent = new Intent(Subject.this, ChapEnglish.class);
                startActivity(intent);
            }
        }));

        btnSubMath.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btnSubMath.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null, null);
                if (tp.equals("Hindi")) {
                    Intent intent = new Intent(Subject.this, mathHindi.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Subject.this, ChapMath.class);
                    startActivity(intent);
                }
            }
        }));

        btnSubHis.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btnSubHis.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null, null);
                Intent intent = new Intent(Subject.this, ChapHistory.class);
                startActivity(intent);
            }
        }));

        btnSubGk.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btnSubGk.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null, null);
                if (tp.equals("Hindi")) {
                    Intent intent = new Intent(Subject.this, GkHindi.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Subject.this, ChapGK.class);
                    startActivity(intent);
                }
            }
        }));

        btnSubInternet.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btnSubInternet.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override
            public void onDoubleClick(View view) {
                t1.speak("Double tap", TextToSpeech.QUEUE_FLUSH, null, null);
                Intent intent = new Intent(Subject.this, Internet.class);
                startActivity(intent);
            }
        }));
    }

    public void ttsFirstPage() {
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                t1.setSpeechRate(0.8f);
                t1.setPitch(1.0f);
                t1.speak(tts, TextToSpeech.QUEUE_FLUSH, null, null);

            }
        }, 500);
        return;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (displayText.Flag_bookmark == 1) {
            tts = "Do you want to continue from last bookmark yes or no";
            ttsFirstPage();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    speechtotext();
                }
            }, 5500);


        }

    }

    private void speechtotext() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException e) {
            Log.e("EXCEPTION genereted", e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.get(0).equals("yes")) {
                        DBhelper db = new DBhelper(this);
                        ArrayList<String> arrayList;
                        arrayList = db.selectbook();
                        String wo = arrayList.get(0);
                        ArrayList<String> arrayList1;
                        arrayList1 = db.selectbook2();
                        String fi = arrayList1.get(0);
                        Intent i1 = new Intent(Subject.this, displayText.class);
                        Bundle b1 = new Bundle();
                        b1.putString("word", wo);
                        b1.putString("fi", fi);
                        i1.putExtras(b1);
                        i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i1);
                        finish();
                    } else {
                        displayText.Flag_bookmark = 0;
                        recreate();

                    }
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (t1 != null) {
            t1.shutdown();
        }
        super.onPause();
    }


}


