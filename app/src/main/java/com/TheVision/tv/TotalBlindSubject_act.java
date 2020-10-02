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
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;

public class TotalBlindSubject_act extends AppCompatActivity {
    TextToSpeech t1;
    String tts,tp;
    int ch;
    Button b1,b2,b3,b4,b5;
  static public String chooseSub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_total_blind_subject_act);
        SharedPreferences sharedPreferences = getSharedPreferences("UserType",MODE_PRIVATE);
        tp =sharedPreferences.getString("lang","");
        init();
        if(displayText.Flag_bookmark==1)
        {
            //nothing for do.
        }
        else
        {
            tts="Choose 1 to 5 for Subject";
            ttsFirstPage();
            sttHandle();
        }



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
    @Override
    protected void onResume() {

        if(displayText.Flag_bookmark==1)
        {
            tts="Do you want to continue from last bookmark yes or no";
            ttsFirstPage();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    speechtotext();
                }
            },5500);


        }
        super.onResume();
    }

    private void speechtotext() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
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
        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //
                    if (result.get(0).equals("yes")) {
                        DBhelper db = new DBhelper(this);
                        ArrayList<String> arrayList;
                        arrayList=db.selectbook();
                        String wo= arrayList.get(0);
                        ArrayList<String> arrayList1;
                        arrayList1=db.selectbook2();
                        String fi= arrayList1.get(0);
                        Intent i1 = new Intent(this,displayText.class);
                        Bundle b1 = new Bundle();
                        b1.putString("word",wo);
                        b1.putString("fi",fi);
                        i1.putExtras(b1);
                        i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i1);
                        finish();
                    }
                    else{
                        displayText.Flag_bookmark=0;

                    }
                    //
                  try {
                        if (result.get(0).equals("yes"))
                        {
                            //nothing to do.
                        }else
                        {
                            ch = Integer.parseInt(result.get(0));
                            choise(ch);
                        }
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
                b1.setBackgroundColor(getResources().getColor(R.color.subEnglish,null));
                b1.setTextColor(getResources().getColor(R.color.text,null));

                tts = "you Selected 1 And This is for english ";
                ttsFirstPage();
                chooseSub="English";
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        Intent i = new Intent(TotalBlindSubject_act.this,TotalB_chap_act.class);
                        startActivity(i);
                    }
                }, 5500);

                break;
            case 2:
                b2.setBackgroundColor(getResources().getColor(R.color.subMath,null));
                b2.setTextColor(getResources().getColor(R.color.text,null));
                tts = "you Selected 2 And This is for Mathematics ";
                ttsFirstPage();
                chooseSub="Mathematics";
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        if(tp.equals("Hindi"))
                        {
                            Intent intent = new Intent(TotalBlindSubject_act.this, mathHindi.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(TotalBlindSubject_act.this, ChapMath.class);
                            startActivity(intent);
                        }
                    }
                }, 5500);

                break;
            case 3:
                b3.setBackgroundColor(getResources().getColor(R.color.subHis,null));
                b3.setTextColor(getResources().getColor(R.color.text,null));
                tts = "you Selected 3 And This is for History ";
                ttsFirstPage();
                chooseSub="History";
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        Intent i5 = new Intent(TotalBlindSubject_act.this,hisTB.class);
                        startActivity(i5);

                    }
                }, 5500);

                break;
            case 4:
                b4.setBackgroundColor(getResources().getColor(R.color.subGK,null));
                b4.setTextColor(getResources().getColor(R.color.text,null));
                tts = "you Selected 4 And This is for General Knowledge ";
                ttsFirstPage();
                chooseSub="General Knowledge";
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        if(tp.equals("Hindi"))
                        {
                            Intent intent = new Intent(TotalBlindSubject_act.this, GkHindi.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(TotalBlindSubject_act.this,ChapGK.class);
                            startActivity(intent);
                        }
                    }
                }, 5500);

                break;
            case 5:
                b5.setBackgroundColor(getResources().getColor(R.color.subInternet,null));
                b5.setTextColor(getResources().getColor(R.color.text,null));
                tts = "you Selected 5 And This is for Internet Search ";
                ttsFirstPage();
                chooseSub="Internet Search";
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        speechtotext();
                        Intent i4 = new Intent(TotalBlindSubject_act.this,Internet.class);
                        startActivity(i4);
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
        b1= (Button) findViewById(R.id.btnTBeng);
        b2= (Button) findViewById(R.id.btnTBmath);
        b3= (Button) findViewById(R.id.btnTBhis);
        b4= (Button) findViewById(R.id.btnTBGK);
        b5= (Button) findViewById(R.id.btnTBinternet);

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
