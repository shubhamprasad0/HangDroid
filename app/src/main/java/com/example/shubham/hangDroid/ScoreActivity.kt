package com.example.shubham.hangDroid

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        showScores()
    }

    private fun showScores() {
        val preferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE)
        val scores = preferences.getString("SCORES", "NO SCORE")
        val scoresArray = scores.split("\n")
        val scoresLinearLayout = findViewById<LinearLayout>(R.id.scores_linear_layout)
        for (score in scoresArray) {
            val newTextView = layoutInflater.inflate(R.layout.score_layout_text_view, null) as TextView
            newTextView.text = score
            scoresLinearLayout.addView(newTextView)
        }
    }
}
