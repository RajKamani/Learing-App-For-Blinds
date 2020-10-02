package com.TheVision.tv;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Internet extends AppCompatActivity {

    private TextView textView, ee;
    String description, email;

    TextToSpeech t1;
    String response = "";
    Elements paragraph;
    DatabaseReference databaseReference;
    FirebaseUser user;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        textView = (TextView) findViewById(R.id.TextView01);
        user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
        databaseReference = FirebaseDatabase.getInstance().getReference("Internet_search");
        ee = (TextView) findViewById(R.id.ed);
        response = "click center left for input and right for enter";
        ttsFirstPage();

    }

    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        startActivityForResult(intent, 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ee.setText(result.get(0));
                    description = result.get(0);
                }
                break;


        }

    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            Document doc = null;
            try {
                doc = Jsoup.connect(urls[0]).get();
                paragraph = doc.select("p");
            } catch (IOException e) {
                e.printStackTrace();
            }
            response = paragraph.text();
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            textView.setText(response);
            response = textView.getText().toString();
            ttsFirstPage();
        }
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
                t1.speak(response, TextToSpeech.QUEUE_FLUSH, null, null);

            }
        }, 500);
    }


    public void onPause() {
        if (t1 != null) {
            t1.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i1 = new Intent(this, logoutScr.class);
        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i1);
    }

    public void readWebpage(View view) {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute("http://en.m.wikipedia.org/wiki/Special:search?search=" + ee.getText().toString());
        addDescription();

    }

    private void addDescription() {
        String id = databaseReference.push().getKey();
        internetDataStore internetDataStore = new internetDataStore(email, description);
        databaseReference.child(id).setValue(internetDataStore);
    }


}
