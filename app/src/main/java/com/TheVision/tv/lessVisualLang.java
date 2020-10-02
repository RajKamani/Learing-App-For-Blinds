package com.TheVision.tv;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class lessVisualLang extends AppCompatActivity implements View.OnClickListener {

    String tts;
    TextToSpeech t1;
    Button eng,hindi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_less_visual_lang);

        eng= (Button) findViewById(R.id.btnEngLang);
        hindi= (Button) findViewById(R.id.btnHindiLang);
        eng.setOnClickListener(this);
        hindi.setOnClickListener(this);

        tts="choose Language click top for English and down for Hindi";
        ttsFirstPage();


    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnEngLang:
                                DBhelper.lang1="English";
                                setNextintent();
                                break;

            case R.id.btnHindiLang:

                                DBhelper.lang1="Hindi";
                                setNextintent();
                                break;

        }


    }
    private void setNextintent()
    {
        Intent nextintent=new Intent(this,usertype.class);
        t1.shutdown();
        startActivity(nextintent);
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




}
