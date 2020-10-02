package com.TheVision.tv;

import android.os.AsyncTask;
import android.util.Log;

public class MyCountWord {
    AsyncTask<Void, Void, Void> asyncTask;
    static int words = 0;
    static boolean is_count = true;

    void countWords() {
        asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                while (is_count) {
                    try {
                        Thread.sleep((long) 800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    words++;
                    Log.d("Here", "Words = " + words);

                }

                return null;
            }
        };
        asyncTask.execute();
    }
}
