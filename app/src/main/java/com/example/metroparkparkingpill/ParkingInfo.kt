package com.example.metroparkparkingpill

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_parking_info.*


class ParkingInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_info)

        val parkingspaces =

            recyclerView.apply {
                adapter = ParkingSpace();
                layoutManager = LinearLayoutManager(this@ParkingInfo);
            }
    }
}