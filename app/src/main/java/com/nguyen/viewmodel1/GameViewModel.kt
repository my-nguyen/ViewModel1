package com.nguyen.viewmodel1

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _score = 0
    val score: Int
        get() = _score
    private var currentWordCount = 0
    private lateinit var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    private fun getNextWord2() {
        // 1. Get a random word from the allWordsList
        currentWord = allWordsList.random()
        // 2. Create a scrambled word by scrambling the letters in the currentWord
        val scrambled = currentWord.toCharArray()
        scrambled.shuffle()
        // 3. Handle the case where the scrambled word is the same as the unscrambled word
        while (String(scrambled) == currentWord)
            scrambled.shuffle()
        // 4. Make sure you don't show the same word twice during the game
        if (wordsList.contains(currentWord))
            getNextWord()
        else {
            _currentScrambledWord = String(scrambled)
            ++currentWordCount
            wordsList.add(currentWord)
        }
    }

    /*
    * Updates currentWord and currentScrambledWord with the next word.
    */
    private fun getNextWord() {
        do {
            // Get a random word from the allWordsList,
            currentWord = allWordsList.random()
            // make sure you don't show the same word twice during the game
        } while (wordsList.contains(currentWord))
        val scrambled = currentWord.toCharArray()
        do {
            // scramble the letters in the currentWord
            scrambled.shuffle()
            // handle the case where the scrambled word is the same as the unscrambled word
        } while (String(scrambled) == currentWord)
        _currentScrambledWord = String(scrambled)
        ++currentWordCount
        wordsList.add(currentWord)
    }

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    private fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else
            false
    }
}