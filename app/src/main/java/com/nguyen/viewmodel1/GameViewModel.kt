package com.nguyen.viewmodel1

import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    private var score = 0
    private var currentWordCount = 0
    private var _currentScrambledWord = "test"
    val currentScrambledWord: String
        get() = _currentScrambledWord
}