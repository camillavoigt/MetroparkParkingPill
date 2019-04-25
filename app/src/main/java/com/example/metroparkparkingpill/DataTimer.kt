package com.example.metroparkparkingpill

import android.support.v7.app.AppCompatActivity
import kotlin.concurrent.fixedRateTimer

class DataTimer : AppCompatActivity() {

    fun runTimer() {
        fixedRateTimer("timer", false, 0, 60000) {
            DataStorage.getInstance(this@DataTimer).FetchData()
        }
    }

}