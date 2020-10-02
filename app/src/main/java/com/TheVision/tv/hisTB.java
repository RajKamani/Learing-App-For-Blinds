package com.TheVision.tv;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;

public class hisTB extends AppCompatActivity {
    TextToSpeech t1;
    String tts;
    int ch;
    Button b1,b2,b3,b4,b5;
    Bundle  bundle;
    String tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.his_tb);
        bundle = new Bundle();
        init();
        tts="Choose 1 to 5 for Chapter of " + TotalBlindSubject_act.chooseSub;
        Log.e("log",TotalBlindSubject_act.chooseSub);
        ttsFirstPage();
        sttHandle();
        SharedPreferences sharedPreferences = getSharedPreferences("UserType",MODE_PRIVATE);
        tp =sharedPreferences.getString("lang","");
    }
    public  void ttsFirstPage()
    {
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        final Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                t1.setSpeechRate(0.8f);
                t1.setPitch(1.0f);
                t1.speak(tts, TextToSpeech.QUEUE_FLUSH, null, null);

            }
        },500);
    }

    private void speechtotext() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
        Log.d("Here","Inside speech");
        try {
            startActivityForResult(intent, 100);
        }
        catch (ActivityNotFoundException e) {
            Log.e("EXCEPTION genereted", e.getMessage());
        }
    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Here", "Inside onActivity");
        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    try {
                        ch = Integer.parseInt(result.get(0));
                        Log.e("log",ch+"");
                        choise(ch);
                    }catch (NumberFormatException a)
                    {
                        tts="This is Text Choose number between 1 to 5";
                        ttsFirstPage();
                        sttHandle();
                    }
                }
            }
        }
    }

    private void choise(int ch1) {

        switch (ch1) {
            case 1:
                b1.setBackgroundColor(getResources().getColor(R.color.subHis,null));
                b1.setTextColor(getResources().getColor(R.color.text,null));
                tts = "you Selected 1 And This is for "+b1.getText().toString();
                ttsFirstPage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        Intent i1 =new Intent(hisTB.this,displayText.class);
                        if(tp.equals("Hindi"))
                        {
                            bundle.putString("file","6hin.txt");
                        }else
                        {
                            bundle.putString("file","6.txt");
                        }
                        i1.putExtras(bundle);
                        startActivity(i1);
                    }
                }, 5500);

                break;
            case 2:
                b2.setBackgroundColor(getResources().getColor(R.color.subGK,null));
                b2.setTextColor(getResources().getColor(R.color.text,null));
                tts = "you Selected 2 And This is for"+b2.getText().toString();
                ttsFirstPage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        Intent i2 =new Intent(hisTB.this,displayText.class);
                        if(tp.equals("Hindi"))
                        {
                            bundle.putString("file","7hin.txt");
                        }else
                        {
                            bundle.putString("file","7.txt");
                        }
                        i2.putExtras(bundle);
                        startActivity(i2);
                    }
                }, 5500);

                break;
            case 3:
                b3.setBackgroundColor(getResources().getColor(R.color.subInternet,null));
                b3.setTextColor(getResources().getColor(R.color.text,null));
                tts = "you Selected 3 And This is for"+b3.getText().toString();
                ttsFirstPage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        Intent i3 =new Intent(hisTB.this,displayText.class);
                        if(tp.equals("Hindi"))
                        {
                            bundle.putString("file","8hin.txt");
                        }else
                        {
                            bundle.putString("file","8.txt");
                        }
                        i3.putExtras(bundle);
                        startActivity(i3);
                    }
                }, 5500);

                break;
            case 4:
                b4.setBackgroundColor(getResources().getColor(R.color.subMath,null));
                b4.setTextColor(getResources().getColor(R.color.text,null));
                tts = "you Selected 4 And This is for "+b4.getText().toString();
                ttsFirstPage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        Intent i4 =new Intent(hisTB.this,displayText.class);
                        if(tp.equals("Hindi"))
                        {
                            bundle.putString("file","9hin.txt");
                        }else
                        {
                            bundle.putString("file","9.txt");
                        }
                        i4.putExtras(bundle);
                        startActivity(i4);
                    }
                }, 5500);

                break;
            case 5:
                b5.setBackgroundColor(getResources().getColor(R.color.subEnglish,null));
                b5.setTextColor(getResources().getColor(R.color.text,null));
                tts = "you Selected 5 And This is for"+b5.getText().toString();
                ttsFirstPage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        Intent i5 =new Intent(hisTB.this,displayText.class);
                        if(tp.equals("Hindi"))
                        {
                            bundle.putString("file","10hin.txt");
                        }else
                        {
                            bundle.putString("file","10.txt");
                        }
                        i5.putExtras(bundle);
                        startActivity(i5);
                    }
                }, 5500);

                break;

            default:
                tts = "Please Select between 1 To 5";
                ttsFirstPage();
                sttHandle();
                break;
        }
    }

    public void init()
    {
        b1= (Button) findViewById(R.id.btnch1);
        b2= (Button) findViewById(R.id.btnch2);
        b3= (Button) findViewById(R.id.btnch3);
        b4= (Button) findViewById(R.id.btnch4);
        b5= (Button) findViewById(R.id.btnch5);

    }
    public void sttHandle()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                speechtotext();
            }
        }, 4000);
    }

    @Override
    protected void onPause() {
        if (t1 != null) {
            t1.shutdown();
        }
        super.onPause();

    }
}
