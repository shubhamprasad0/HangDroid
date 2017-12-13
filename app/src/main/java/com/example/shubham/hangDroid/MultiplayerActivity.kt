package com.example.shubham.hangDroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MultiplayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplayer)
    }

    fun sendWordToOtherPlayer(view: View) {
        val editText = findViewById<View>(R.id.input_word) as EditText
        val word = editText.text.toString().toUpperCase().trim()
        editText.setText("")
        val intent = Intent(this, MultiplayerGameActivity::class.java)
        intent.putExtra("WORD", word)
        startActivity(intent)
    }
}
