package com.smy.wordguessgame.timer

import android.content.Context
import android.os.CountDownTimer

class CustomCountDownTimer(
    val context: Context,
    val listener: TimerListener,
    millisInFuture: Long,
    countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

    var timeLeft = millisInFuture
    override fun onTick(millisUntilFinished: Long) {
        timeLeft = millisUntilFinished
        listener.onTimerTick(millisUntilFinished)
    }

    override fun onFinish() {
        listener.onTimerFinish()
    }

}