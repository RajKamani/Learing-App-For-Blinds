package com.TheVision.tv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Raj kamani on 12/29/2018.
 */

public class Splashcreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView img = (ImageView) findViewById(R.id.imageView);

        Thread ime = new Thread() {
            @Override
            public void run() {

                try {
                    sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent splaceIntent = new Intent("com.TheVision.tv.MAINACTIVITY");
                    startActivity(splaceIntent);
                }
            }
        };
        ime.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

 }


}