package com.example.shubham.hangDroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSinglePlayerGame(v: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun startMultiPlayerGame(v: View) {
        val intent = Intent(this, MultiplayerActivity::class.java)
        startActivity(intent)
    }

    fun openScores(v: View) {
        val intent = Intent(this, ScoreActivity::class.java)
        startActivity(intent)
    }
}