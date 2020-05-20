package com.android.labo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class CrashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash)

        val buttonCrash = findViewById<AppCompatButton>(R.id.button_crash)
        buttonCrash.setOnClickListener {
            throw RuntimeException("Boom!")
        }
    }
}
