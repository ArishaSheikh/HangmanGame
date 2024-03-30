package com.example.hangmangame

//import android.app.GameState
import kotlin.random.Random

class GameManager {
    private var lettersUsed: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private val maxTries = 7
    private var currentTries = 0
    private var drawable: Int = R.drawable.game_0

    fun startNewGame() : Any {               //fun startNewGame() : GameState {
        lettersUsed = ""
        currentTries = 0
        drawable = R.drawable.game_7
        val randomIndex = Random.nextInt(0,GameConstants.words.size)
        wordToGuess = GameConstants.words[randomIndex]
        generateUnderscores(wordToGuess)
        return getGameState()
    }

    fun generateUnderscores(word : String){
        val sb = StringBuilder()       // allows you to efficiently modify and build a string
        word.forEach { char ->
            if (char == '/'){
                sb.append('/')     // write words using append
        }
         else
         {
             sb.append("_")
            }
        }
        underscoreWord = sb.toString()    // to convert it into a string and then pass value to underscoreword
    }

    fun play(letter : Char): Any {         //fun play(letter : Char): GameState {
        if (lettersUsed.contains(letter)) {
            return GameState.Running(lettersUsed, underscoreWord, drawable)
        }

        lettersUsed += letter
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed {index,char ->
            if (char.equals(letter, true)) {   // true :  to do a case-insensitive comparison
                                                           // (meaning it doesn't care if letters are uppercase or lowercase).
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord    // "h_ll_ w_rld"
        indexes.forEach{index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index,letter) }
            finalUnderscoreWord = sb.toString()

        }

        if (indexes.isEmpty()){
            currentTries++
        }
        underscoreWord = finalUnderscoreWord  //it updated whether the guess is correct or wrong
        return getGameState()
    }

    private fun getHangmanDrawable() : Int{

        return when(currentTries){
            0 -> R.drawable.game_0
            1 -> R.drawable.game_1
            2 -> R.drawable.game_2
            3 -> R.drawable.game_3
            4 -> R.drawable.game_4
            5 -> R.drawable.game_5
            6 -> R.drawable.game_6
            7 -> R.drawable.game_7
            else -> R.drawable.game_7

        }
    }

    private fun getGameState() : Any {              //   private fun getGameState() : GameState {
        if (underscoreWord.equals(wordToGuess , true)){
            return GameState.Won(wordToGuess)
        }

        if (currentTries == maxTries){
            return GameState.Lost(wordToGuess)
        }

        drawable = getHangmanDrawable()
        return GameState.Running(lettersUsed , underscoreWord , drawable)

    }

}