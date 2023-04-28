package com.smy.wordguessgame.timer

import android.content.Context
import android.os.CountDownTimer

class CustomCountDownTimer(
    val context: Context,
    val listener: TimerListener,
    millisInFuture: Long,
    countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

    override fun onTick(millisUntilFinished: Long) {
        listener.onTimerTick(millisUntilFinished)
    }

    override fun onFinish() {
        listener.onTimerFinish()
    }
}