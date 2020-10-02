package com.TheVision.tv;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class displayText extends AppCompatActivity {

    TextView t1;
    Button btnbook;
    TextToSpeech t2, t3;
    String s = "";
    String s3, s4, file;
    String tp, tts;
    int index;
    int pos;
    public static int Flag_bookmark = 0;
    int[] a;

    String wordc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text);
        t1 = (TextView) findViewById(R.id.txt);
        btnbook = (Button) findViewById(R.id.btnbook);
        Bundle b1 = getIntent().getExtras();
        s3 = b1.getString("file");
        wordc = b1.getString("word");
        s4 = b1.getString("fi");

        SharedPreferences sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);
        tp = sharedPreferences.getString("lang", "");

        tts = "Click top for Bookmark";
        ttsFirstPage();
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                booked();
            }
        });
    }

    public void ttsFirstPage() {
        t3 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t3.setLanguage(Locale.UK);
                }
            }
        });

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                t3.setSpeechRate(0.8f);
                t3.setPitch(1.0f);
                t3.speak(tts, TextToSpeech.QUEUE_FLUSH, null, null);

            }
        }, 500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Flag_bookmark == 1) {
            file = s4;
        } else {
            file = s3;
        }

        try {
            InputStream inputStream = getAssets().open(file);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            s = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        t1.setText(s);
        t1.post(new Runnable() {
            @Override
            public void run() {
                index = t1.getLineCount();
                a = new int[index];
            }
        });
        t1.post(new Runnable() {
            @Override
            public void run() {
                Log.e("lines", t1.getLineCount() + "");
            }
        });

        if (tp.equals("Hindi")) {
            t2 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        t2.setLanguage(new Locale("hin", "IND", ""));
                        t2.setSpeechRate(0.8f);
                        t2.setPitch(1.0f);
                        MyCountWord myCountWord = new MyCountWord();
                        myCountWord.countWords();
                        if (wordc != null) {
                            final Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    pos = Integer.valueOf(wordc);
                                    t2.speak(t1.getText().toString().substring(pos, 3999), TextToSpeech.QUEUE_ADD, null);

                                }
                            }, 2000);

                        } else {
                            final Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    t2.speak(t1.getText().toString().substring(0, 3999), TextToSpeech.QUEUE_ADD, null);

                                }
                            }, 2000);
                        }


                    }
                }
            });
        } else {
            t2 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {

                        t2.setLanguage(Locale.UK);
                        t2.setSpeechRate(0.8f);
                        t2.setPitch(1.0f);
                        MyCountWord myCountWord = new MyCountWord();
                        myCountWord.countWords();
                        if (wordc != null) {
                            final Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    pos = Integer.valueOf(wordc);
                                    t2.speak(t1.getText().toString().substring(pos, 3999), TextToSpeech.QUEUE_ADD, null);

                                }
                            }, 2000);
                        } else {
                            final Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    t2.speak(t1.getText().toString().substring(0, 3999), TextToSpeech.QUEUE_ADD, null);

                                }
                            }, 2000);
                        }


                    }
                }
            });
        }


    }


    @Override
    protected void onPause() {
        super.onPause();
        t2.shutdown();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i1 = new Intent(displayText.this, logoutScr.class);
        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i1);
    }

    private void booked() {
        MyCountWord.is_count = false;
        DBhelper dBbook = new DBhelper(this);
        DBhelper.file = s3;
        DBhelper.wordC = String.valueOf(MyCountWord.words);

        if (dBbook.insert2()) {
            tts = "Bookmark completed";
            ttsFirstPage();
            Flag_bookmark = 1;
            t2.shutdown();

        }
    }

}
