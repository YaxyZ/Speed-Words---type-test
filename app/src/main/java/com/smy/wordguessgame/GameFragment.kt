package com.smy.wordguessgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.smy.wordguessgame.timer.CustomCountDownTimer
import com.smy.wordguessgame.timer.TimerListener


class GameFragment : Fragment() {

    private lateinit var scoreTextView: TextView
    private lateinit var wordTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var textInput : TextInputEditText

    private lateinit var gameViewModel : GameViewModel

    private lateinit var timer: CustomCountDownTimer

    private val timerInterval = 1000L // 1 second
    private val timerDuration = 60000L // 60 seconds


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        //        val strikeList = listOf(findViewById<TextView>(R.id.strike1),
//                                            findViewById(R.id.strike2),
//                                            findViewById(R.id.strike3))

        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        scoreTextView = view.findViewById(R.id.gameScoreTextView)
        wordTextView = view.findViewById(R.id.wordTextView)
        timerTextView = view.findViewById(R.id.timerTextView)
        textInput = view.findViewById(R.id.textInput)

        gameViewModel.currentWord.observe(viewLifecycleOwner) {
            wordTextView.text = it.toString()
        }
        gameViewModel.score.observe(viewLifecycleOwner){
            scoreTextView.text = it.toString()
        }

        timer = CustomCountDownTimer(requireContext(),
            object : TimerListener {
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
            if (gameViewModel.checkWord(textInput.text.toString())) {
//                if (!gameViewModel.checkNextWordExists()) //change this later to death dialog
                textInput.text?.clear()
            }

        }
        return view
    }

    private fun showEndingDialog(timer:CustomCountDownTimer){
        val alertDialog: AlertDialog = this.let {
            val builder = AlertDialog.Builder(requireContext())
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
                    requireActivity().finish()
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