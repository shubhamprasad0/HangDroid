package com.example.shubham.hangDroid

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class GameOverActivity : AppCompatActivity() {

    private var points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        points = intent.getIntExtra("SCORE", 0)
        val textView = findViewById<View>(R.id.points) as TextView
        textView.text = points.toString()
    }

    /**
     * Save the score
     */
    fun saveScore(v: View) {
        // get reference to the EditText
        val editText = findViewById<EditText>(R.id.enterName)

        // get player name
        val name = editText.text.toString()

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show()
        } else {
            // preference to save the user name and score
            val preferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE)
            val editor = preferences.edit()

            // get previous scores
            val previousScores = preferences.getString("SCORES", "")

            // append new score to the previous scores
            editor.putString("SCORES", "$name $points \n$previousScores")
            editor.apply()
            finish()
        }

    }
}
