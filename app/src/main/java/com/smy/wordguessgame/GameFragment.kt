package com.smy.wordguessgame

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.smy.wordguessgame.timer.CustomCountDownTimer
import com.smy.wordguessgame.timer.TimerListener


class GameFragment : Fragment() {

    private lateinit var scoreTextView: TextView
    private lateinit var wordTextView: TextView
    private lateinit var timerTextView: TextView
    private lateinit var textInput : TextInputEditText
    private lateinit var pauseButton : ImageView

    private lateinit var gameViewModel : GameViewModel

    private var dialogs = mutableListOf<Dialog>()

    private lateinit var timer: CustomCountDownTimer

    private val timerInterval = 1000L // 1 second
    private val timerDuration = 60000L // 60 seconds
    private var timeLeft = 0L


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        //        val strikeList = listOf(findViewById<TextView>(R.id.strike1),
//                                            findViewById(R.id.strike2),
//                                            findViewById(R.id.strike3))
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            onPause()
        }

        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        scoreTextView = view.findViewById(R.id.gameScoreTextView)
        wordTextView = view.findViewById(R.id.wordTextView)
        timerTextView = view.findViewById(R.id.timerTextView)
        textInput = view.findViewById(R.id.textInput)

        pauseButton = view.findViewById(R.id.gamePauseButton)



        gameViewModel.currentWord.observe(viewLifecycleOwner) {
            wordTextView.text = it.toString()
        }
        gameViewModel.score.observe(viewLifecycleOwner){
            scoreTextView.text = it.toString()
        }

        setTimer(timerDuration)

        pauseButton.setOnClickListener {
            showPauseDialog()
        }
        gameViewModel.getWord()

        textInput.doAfterTextChanged {
            if (gameViewModel.checkWord(textInput.text.toString())) {
//                if (!gameViewModel.checkNextWordExists()) //change this later to death dialog
                textInput.text?.clear()
            }

        }
        return view
    }

    override fun onPause() {
        timeLeft = timer.timeLeft
        timer.cancel()
        showPauseDialog()
        super.onPause()
    }


    override fun onDestroyView() {
        timer.cancel()
        for(dialog in dialogs)
            if(dialog.isShowing) dialog.dismiss()
        super.onDestroyView()
    }


    private fun showEndingDialog(timer:CustomCountDownTimer){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_ending)
        dialog.setCancelable(false)

        val tryAgainButton:Button = dialog.findViewById(R.id.endTryAgainButton)
        val settingsButton:Button = dialog.findViewById(R.id.pauseSettingsButton)
        val mainMenuButton:Button = dialog.findViewById(R.id.pauseMainMenuButton)
        dialog.findViewById<TextView>(R.id.endTypingResult).text = "${gameViewModel.score.value} WPM"

        tryAgainButton.setOnClickListener{
            gameViewModel.resetGame()
            timer.cancel()
            timer.start()
            dialog.dismiss()
        }

        settingsButton.setOnClickListener {
            val action = GameFragmentDirections.actionGameFragmentToSettingsFragment2()
            findNavController().navigate(action)
            dialog.dismiss()
        }

        mainMenuButton.setOnClickListener {
            val action = GameFragmentDirections.actionGameFragmentToHomeFragment()
            findNavController().navigate(action)
            dialog.dismiss()
        }
        dialogs.add(dialog)
        dialog.show()


    }

    private fun showPauseDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_pause)
        dialog.setCancelable(false)

        val continueButton:Button = dialog.findViewById(R.id.pauseContinueButton)
        val settingsButton:Button = dialog.findViewById(R.id.pauseSettingsButton)
        val mainMenuButton:Button = dialog.findViewById(R.id.pauseMainMenuButton)

        continueButton.setOnClickListener{
            setTimer(timeLeft)
            dialog.dismiss()
        }

        settingsButton.setOnClickListener {
            val action = GameFragmentDirections.actionGameFragmentToSettingsFragment2()
            findNavController().navigate(action)
            dialog.dismiss() }

        mainMenuButton.setOnClickListener {
            val action = GameFragmentDirections.actionGameFragmentToHomeFragment()
            findNavController().navigate(action)
            dialog.dismiss()
        }
        dialogs.add(dialog)
        dialog.show()
    }

    private fun setTimer(duration:Long,interval:Long = timerInterval) {
         timer = CustomCountDownTimer(requireContext(),
            object : TimerListener {
                override fun onTimerTick(milis: Long) {
                    timerTextView.text = (milis/1000).toString()
                }

                override fun onTimerFinish() {
                    showEndingDialog(timer)
                }
            },
            duration,interval
        )
        timer.start()
    }

    }

