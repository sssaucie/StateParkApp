package com.example.stateparkapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity()
{

    private var timeOut : Long = 3000

    private fun loadSplashScreen() {
        //TODO: why is Handler deprecated? How to update?
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, timeOut)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadSplashScreen()
    }
}