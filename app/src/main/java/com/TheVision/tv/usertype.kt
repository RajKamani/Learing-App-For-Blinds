package com.TheVision.tv

import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import java.util.ArrayList
import java.util.Locale

class usertype : AppCompatActivity(), View.OnClickListener {

    internal var btntotal: Button
    internal var btnless: Button
    internal var tts: String
    internal var db: DBhelper
    internal var t1: TextToSpeech


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usertype)

        tts = "choose user type click top for Total blind and down for less visual"
        ttsFirstPage()

        btnless = findViewById(R.id.btnLessVisual) as Button
        btntotal = findViewById(R.id.btnTotal) as Button

        btnless.setOnClickListener(this)
        btntotal.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLessVisual -> DBhelper.typeuser = "LessVisual"

            R.id.btnTotal -> DBhelper.typeuser = "TotalBlind"
        }

        db = DBhelper(this)

        if (db.insert()) {
            Toast.makeText(this, "Record inserted", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }

        val arrayList: ArrayList<String>
        arrayList = db.select()
        val s = arrayList[0]
        val arrayList1: ArrayList<String>
        arrayList1 = db.selectlang()
        val s1 = arrayList1[0]
        val sharedPreferences = getSharedPreferences("UserType", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user1", s)
        editor.putString("lang", s1)
        editor.apply()
        if (arrayList[0] == "TotalBlind") {
            val ine = Intent(this@usertype, TotalBlindSubject_act::class.java)
            ine.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            t1.shutdown()
            startActivity(ine)
        }
        if (arrayList[0] == "LessVisual") {
            val ine = Intent(this@usertype, Subject::class.java)
            ine.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            t1.shutdown()
            startActivity(ine)
        }


    }


    fun ttsFirstPage() {
        t1 = TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                t1.language = Locale.UK
            }
        })

        val h = Handler()
        h.postDelayed({
            t1.setSpeechRate(0.8f)
            t1.setPitch(1.0f)
            t1.speak(tts, TextToSpeech.QUEUE_FLUSH, null, null)
        }, 500)
    }


}
