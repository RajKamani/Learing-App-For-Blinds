//package Declaration
package com.TheVision.tv;
//Import Statments

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaration
    Button btnlog, btnsign;
    TextToSpeech t1;
    String email = "", password = "";
    String tts;
    int flag = 0;
    boolean loginflag = false;
    boolean signupflag = false;
    ProgressBar p1;
    DBhelper db;
    FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        btnlog = (Button) findViewById(R.id.btnLogin);
        btnsign = (Button) findViewById(R.id.btnSignUp);
        p1 = (ProgressBar) findViewById(R.id.pb);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {

            tts = "Click Upper part of your screen for Login and Down part of your screen for SignUp";
            ttsFirstPage();
        }

        btnlog.setOnClickListener(this);
        btnsign.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences("UserType", MODE_PRIVATE);
        String tp = sharedPreferences.getString("user1", "");
        if (user != null) {

            if (tp.equals("TotalBlind")) {
                Intent i1 = new Intent(MainActivity.this, TotalBlindSubject_act.class);

                startActivity(i1);
                finish();
            } else if (tp.equals("LessVisual")) {

                Intent intent = new Intent(MainActivity.this, Subject.class);
                startActivity(intent);
                finish();
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnlog.setEnabled(true);
                btnsign.setEnabled(true);
            }
        }, 5000);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnLogin:
                loginflag = true;
                data();
                break;

            case R.id.btnSignUp:
                signupflag = true;
                data();
                break;

        }

    }

    public void data() {
        if (flag == 0) {
            tts = "Your Email";
            Log.d("Here", "Inside data");
            ttsFirstPage();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    speechtotext();
                }
            }, 3000);


        }
        if (flag == 1) {
            tts = "Your Password";
            ttsFirstPage();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    speechtotext();
                }
            }, 3000);


        }

    }


    private void speechtotext() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        Log.d("Here", "Inside speech");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException e) {
            e.getMessage();
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
                    result.trimToSize();


                    if (flag == 0) {
                        email = result.get(0).toLowerCase().trim();
                        email = email.replaceAll(" ", "");
                        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            tts = "Your Email is invalid";
                            ttsFirstPage();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    recreate();
                                }
                            }, 3000);
                            return;
                        }

                        flag++;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btnsign.performClick();
                            }
                        }, 2000);

                        return;
                    }
                    if (flag == 1) {
                        password = result.get(0).toLowerCase().trim();
                        password = password.replaceAll(" ", "");

                        if (loginflag) {
                            p1.setVisibility(View.VISIBLE);
                            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    p1.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Intent i = new Intent(MainActivity.this, lessVisualLang.class);
                                        startActivity(i);
                                    } else {
                                        tts = "Your Email or password is incorrect";
                                        ttsFirstPage();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                recreate();
                                            }
                                        }, 4000);
                                    }
                                }
                            });
                            loginflag = false;
                            return;

                        }

                        if (signupflag) {
                            p1.setVisibility(View.VISIBLE);
                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    p1.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        tts = "User created please Log in again";
                                        ttsFirstPage();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                recreate();
                                            }
                                        }, 3000);

                                    } else {
                                        tts = "Something went Wrong try again";
                                        ttsFirstPage();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                recreate();
                                            }
                                        }, 3000);
                                    }

                                }
                            });
                            signupflag = false;
                            return;
                        }


                    }

                }
            }


            break;

        }
    }


    //Creation of TextToSpeech method for First Activity.
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
    }


    public void onPause() {
        if (t1 != null) {
            t1.shutdown();
        }
        super.onPause();
    }

    @Override
    protected void onRestart() {
        ttsFirstPage();
        super.onRestart();
    }


}

