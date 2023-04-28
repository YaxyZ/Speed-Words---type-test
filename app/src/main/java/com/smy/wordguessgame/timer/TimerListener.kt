package com.smy.wordguessgame.timer

interface TimerListener {
    fun onTimerTick(milis:Long)
    fun onTimerFinish()
}