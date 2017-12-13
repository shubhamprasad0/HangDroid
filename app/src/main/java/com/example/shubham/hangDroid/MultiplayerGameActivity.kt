package com.example.shubham.hangDroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MultiplayerGameActivity : AppCompatActivity() {

    private var word = ""  // word to be matched
    private var letterFound = false  // is the guessed letter found in word
    private var failCounter = 0  // number of times the user wrongly guessed a letter
    private var guessCounter = 0  // number of letters guessed correctly
    private var score = 0  // game score

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplayer_game)
        getWordFromOtherPlayer()
        createTextViews(word)
    }

    /**
     * Gets the word set by the other player in a multi-player environment
     */
    private fun getWordFromOtherPlayer() {
        word = intent.getStringExtra("WORD")
    }

    /**
     * Retrieving the letter from the editText
     *
     * @param v (The Button click)
     */
    fun introduceLetter(v: View) {
        letterFound = false
        val editText = findViewById<View>(R.id.editText) as EditText
        val letter = editText.text.toString().toUpperCase()
        editText.setText("")


        if (letter.length == 1) {
            checkLetter(letter)
        } else {
            Toast.makeText(this, "Please type one letter", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * This method checks whether the introduced character is in the word or not
     *
     * @param introducedLetter (The letter introduced by the player in the EditText)
     */
    private fun checkLetter(introducedLetter: String) {
        for ((i, char) in word.withIndex()) {
            if (char == introducedLetter[0]) {
                letterFound = true
                showLettersAtIndex(i, char)
                guessCounter++
            }
        }

        // if the word didn't contain the guessed letter
        if (letterFound == false) {
            letterFailed(introducedLetter[0])
        }

        if (guessCounter == word.length) {
            finish()
        }
    }

    /**
     * Clears the game details so as to start a new game with a new word
     */
    private fun clearGameStatus() {

        // clear the necessary counts and flags
        failCounter = 0
        guessCounter = 0
        letterFound = false

        // clear all the failed letters
        clearFailedLetters()

        // clear all the correct letters
        clearCorrectLetters()

        // reset the image to initial level
        resetImage()
    }

    /**
     * Resets the image to the initial level where the droid is not present
     */
    private fun resetImage() {
        val imageView = findViewById<View>(R.id.imageView) as ImageView
        imageView.setImageResource(R.drawable.hangdroid_0)
    }

    /**
     * Clears the correct letters present in the TextViews
     */
    private fun clearCorrectLetters() {
        val correctLettersLayout = findViewById<LinearLayout>(R.id.correct_letters)
        val numCorrectLetters = correctLettersLayout.childCount

        for (i in 0..(numCorrectLetters - 1)) {
            val currentTextView = correctLettersLayout.getChildAt(i) as TextView
            currentTextView.text = "__"
        }
    }

    /**
     * Clears the TextViews containing the correct letters
     */
    private fun clearFailedLetters() {
        val failedLettersLayout = findViewById<LinearLayout>(R.id.wrong_letters)
        val numWrongLetters = failedLettersLayout.childCount
        for (i in 0..(numWrongLetters - 1)) {
            val currentTextView = failedLettersLayout.getChildAt(i) as TextView
            currentTextView.text = ""
        }
    }

    /**
     * This method shows the correct letters at the correct positions
     *
     * @param index (position of the correct letter)
     * @param letterGuessed (the letter guessed correctly)
     */
    private fun showLettersAtIndex(index: Int, letterGuessed: Char) {
        val correctLetters = findViewById<View>(R.id.correct_letters) as LinearLayout
        val textView = correctLetters.getChildAt(index) as TextView
        textView.text = letterGuessed.toString()
    }


    /**
     * This method does the necessary changes required when a player fails a letter
     *
     * @param failedLetter (the wrongly guessed letter)
     */
    private fun letterFailed(failedLetter: Char) {
        // increase the fail counter
        failCounter++

        // increase the hanging intensity of the droid
        changeHangingDroidImage()

        // display the failed letters in the TextView
        displayFailedLetter(failedLetter)
    }

    /**
     * Displays the wrongly guessed letter in a TextView
     */
    private fun displayFailedLetter(failedLetter: Char) {
        val failedLettersLayout = findViewById<LinearLayout>(R.id.wrong_letters)
        val textView = failedLettersLayout.getChildAt(failCounter - 1) as TextView
        textView.text = failedLetter.toString()
    }

    /**
     * Changes the image of the hanging droid as the player fails a letter
     */
    private fun changeHangingDroidImage() {
        val imageView = findViewById<View>(R.id.imageView) as ImageView
        when (failCounter) {
            1 -> imageView.setImageResource(R.drawable.hangdroid_1)
            2 -> imageView.setImageResource(R.drawable.hangdroid_2)
            3 -> imageView.setImageResource(R.drawable.hangdroid_3)
            4 -> imageView.setImageResource(R.drawable.hangdroid_4)
            5 -> imageView.setImageResource(R.drawable.hangdroid_5)
            6 -> {
                showCorrectWord()
                val intent = Intent(this, GameOverActivity::class.java)
                intent.putExtra("SCORE", score)
                startActivity(intent)
                finish()
            }
        }
    }

    /**
     * Creates number of blanks depending upon the length of the word
     *
     * @param word (the word passed by the other player)
     */
    private fun createTextViews(word: String) {
        val linearLayout = findViewById<LinearLayout>(R.id.correct_letters)
        for (letter in word) {
            val newTextView = layoutInflater.inflate(R.layout.letter_text_view, null) as TextView
            linearLayout.addView(newTextView)
        }
    }

    /**
     * Show the correct word as a Toast
     */
    private fun showCorrectWord() {
        Toast.makeText(this, "Correct Word: $word", Toast.LENGTH_LONG).show()
    }
}
