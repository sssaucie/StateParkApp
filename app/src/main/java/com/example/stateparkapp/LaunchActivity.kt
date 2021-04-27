package com.example.stateparkapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.stateparkapp.utilities.TIME_OUT

class LaunchActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()}, TIME_OUT)
    }
}