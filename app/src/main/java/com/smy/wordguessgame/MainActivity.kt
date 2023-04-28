package com.smy.wordguessgame

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.smy.wordguessgame.timer.CustomCountDownTimer
import com.smy.wordguessgame.timer.TimerListener

class MainActivity : AppCompatActivity() {

    private lateinit var scoreTextView: TextView
    private lateinit var wordTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var textInput : TextInputEditText

    private lateinit var gameViewModel : GameViewModel

    private lateinit var timer: CustomCountDownTimer

    private val timerInterval = 1000L // 1 second
    private val timerDuration = 60000L // 60 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val strikeList = listOf(findViewById<TextView>(R.id.strike1),
//                                            findViewById(R.id.strike2),
//                                            findViewById(R.id.strike3))

        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        scoreTextView = findViewById(R.id.gameScoreTextView)
        wordTextView = findViewById(R.id.wordTextView)
        timerTextView = findViewById(R.id.timerTextView)
        textInput = findViewById(R.id.textInput)

        gameViewModel.currentWord.observe(this) {
            wordTextView.text = it.toString()
        }
        gameViewModel.score.observe(this){
            scoreTextView.text = it.toString()
        }

        timer = CustomCountDownTimer(this,
            object : TimerListener{
                override fun onTimerTick(milis: Long) {
                    timerTextView.text = (milis/1000).toString()
                }

                override fun onTimerFinish() {
                    showEndingDialog(timer)
                }
            },
            timerDuration,timerInterval
        )
        timer.start()

        gameViewModel.getWord()

        textInput.doAfterTextChanged {
            if(gameViewModel.checkWord(textInput.text.toString())) {
//                if (!gameViewModel.checkNextWordExists()) //change this later to death dialog
                textInput.text?.clear()
            }
        }

//        use this to get input when pressing the sent button
//        textInput.setOnEditorActionListener{ _, actionId, _ ->
//            if(actionId == EditorInfo.IME_ACTION_SEND){
//                if(gameViewModel.checkWord(textInput.text.toString())) {
//
//                    textInput.text?.clear()
//                }
//                else Toast.makeText(this,"WRONG!",Toast.LENGTH_LONG).show()
//                true
//            }
//            else false
//        }
    }

    private fun showEndingDialog(timer:CustomCountDownTimer){
        val alertDialog: AlertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Try Again"
                ) { dialog, id ->
                    gameViewModel.resetGame()
                    timer.cancel()
                    timer.start()
                    dialog.dismiss()
                }
                setNegativeButton("Quit"
                ) { dialog, id ->
                    finish()
                }
                setMessage("Awesome you did ${gameViewModel.score.value} WPM!")
                setTitle("Game Over")
                setCancelable(false)
            }

            // Create the AlertDialog
            builder.create()
        }
        alertDialog.show()
    }

}