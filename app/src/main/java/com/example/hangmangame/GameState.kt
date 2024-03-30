//package com.example.hangmangame
//
//class GameState {
//    var lettersUsed: Set<Char> = emptySet()
//    var underscoreWord: String = ""
//    var drawable: Int = 0
//    var wordToGuess: String = ""
//
//    fun updateRunningState(lettersUsed:Set<Char>, underscoreWord: String, drawable: Int) {
//        this.lettersUsed = lettersUsed
//        this.underscoreWord = underscoreWord
//        this.drawable = drawable
//    }
//
//    fun updateLostState(wordToGuess: String) {
//        this.wordToGuess = wordToGuess
//    }
//
//    fun updateWonState(wordToGuess: String) {
//        this.wordToGuess = wordToGuess
//    }
//}


     //SEALED CLASS

package com.example.hangmangame

//import android.content.Context
//import androidx.core.content.ContextCompat

sealed class GameState {
     class Running(
        val lettersUsed: String,
        val underscoreWord: String,
        val drawable: Int
    )

     class Lost(val wordToGuess: String)
     class Won(val wordToGuess: String)

}