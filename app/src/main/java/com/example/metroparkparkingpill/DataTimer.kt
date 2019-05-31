package com.example.metroparkparkingpill

import android.support.v7.app.AppCompatActivity
import kotlin.concurrent.fixedRateTimer

class DataTimer : AppCompatActivity() {

    /**
     * Set this method to run every minute
     */
    fun runTimer() {
        fixedRateTimer("timer", false, 0, 60000) {
            DataStorage.getInstance(this@DataTimer).fetchData()
        }
    }

}