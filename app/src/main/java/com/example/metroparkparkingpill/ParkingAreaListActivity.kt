package com.example.metroparkparkingpill

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_parking_area_list.*

class ParkingAreaListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_area_list)

        val thread = Thread {
            val dataStorage = DataStorage()
            val data = dataStorage.FetchData()

            runOnUiThread {
                recyclerView.apply {
                    adapter = ParkingAreaRecyclerAdapter(data)
                    layoutManager = LinearLayoutManager(this@ParkingAreaListActivity)
                }
            }
        }
        thread.start()
    }
}