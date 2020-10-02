package com.TheVision.tv;

import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.util.Locale;
import java.util.Random;

public class mathHindi extends AppCompatActivity {

    TextToSpeech t1;
    Button btn_one, btn_two, btn_three, btn_four;
    TextView Question;


    private QMathHindi question;

    Random random;
    private String answer;
    private int questionLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap_gk);


        question = new QMathHindi();
        questionLength = question.questions.length;

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setSpeechRate(0.8f);
                    t1.setLanguage(new Locale("hin", "IND", ""));
                    t1.speak("चार विकल्प पर क्लिक करें", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });


        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                t1.setSpeechRate(0.8f);
                t1.setPitch(1.0f);
                ttsFirstPage();


            }
        }, 1500);
        random = new Random();


        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_four = (Button) findViewById(R.id.btn_four);


        Question = (TextView) findViewById(R.id.que);


        NextQuestion(random.nextInt(questionLength));


        btn_one.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btn_one.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override
            public void onDoubleClick(View view) {
                if (btn_one.getText() == answer) {
                    t1.speak("आप सही हैं", TextToSpeech.QUEUE_FLUSH, null, null);
                    NextQuestion(random.nextInt(questionLength));
                    ttsFirstPage();
                } else {
                    t1.speak("सही उत्तर है" + answer, TextToSpeech.QUEUE_FLUSH, null, null);
                    NextQuestion(random.nextInt(questionLength));
                    ttsFirstPage();
                }
            }
        }));

        btn_two.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btn_two.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override
            public void onDoubleClick(View view) {
                if (btn_two.getText() == answer) {
                    t1.speak("आप सही हैं", TextToSpeech.QUEUE_FLUSH, null, null);
                    NextQuestion(random.nextInt(questionLength));
                    ttsFirstPage();
                } else {
                    t1.speak("सही उत्तर है " + answer, TextToSpeech.QUEUE_FLUSH, null, null);
                    NextQuestion(random.nextInt(questionLength));
                    ttsFirstPage();
                }
            }

        }));

        btn_three.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btn_three.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override
            public void onDoubleClick(View view) {
                if (btn_three.getText() == answer) {
                    t1.speak("आप सही हैं", TextToSpeech.QUEUE_FLUSH, null, null);

                    NextQuestion(random.nextInt(questionLength));
                    ttsFirstPage();
                } else {
                    t1.speak("सही उत्तर है " + answer, TextToSpeech.QUEUE_FLUSH, null, null);

                    NextQuestion(random.nextInt(questionLength));
                    ttsFirstPage();
                }
            }

        }));

        btn_four.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                t1.speak(btn_four.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }

            @Override
            public void onDoubleClick(View view) {
                if (btn_four.getText() == answer) {
                    t1.speak("आप सही हैं", TextToSpeech.QUEUE_FLUSH, null, null);

                    NextQuestion(random.nextInt(questionLength));
                    ttsFirstPage();
                } else {
                    t1.speak("सही उत्तर है " + answer, TextToSpeech.QUEUE_FLUSH, null, null);
                    NextQuestion(random.nextInt(questionLength));
                    ttsFirstPage();
                }
            }

        }));

    }

    public void ttsFirstPage() {

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("hin", "IND", ""));
                    t1.setSpeechRate(0.8f);
                }
            }
        });

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                t1.speak(Question.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);

            }
        }, 1000);
    }

    private void NextQuestion(int num) {
        Question.setText(question.getQuestion(num));
        btn_one.setText(question.getchoice1(num));
        btn_two.setText(question.getchoice2(num));
        btn_three.setText(question.getchoice3(num));
        btn_four.setText(question.getchoice4(num));

        answer = question.getCorrectAnswer(num);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i1 = new Intent(this, logoutScr.class);
        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i1);
    }
}
