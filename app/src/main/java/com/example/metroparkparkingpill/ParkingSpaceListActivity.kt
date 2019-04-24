package com.example.metroparkparkingpill

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.metroparkparkingpill.Model.ParkingArea
import kotlinx.android.synthetic.main.activity_parking_space_list.*

class ParkingSpaceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_space_list)

        val areaId = intent.getIntExtra("id", -1);

        if(areaId == -1) {
            throw Exception("No id provided. Please Fix Camilla!");
        }

        val thread = Thread {
            val dataStorage = DataStorage()
            val data = dataStorage.FetchData()

            runOnUiThread {
                recyclerView.apply {
                    adapter = ParkingSpaceRecyclerAdapter(data.parkingAreas.find { it.parkingAreaId == areaId }!!.parkingSpaceList)
                    layoutManager = LinearLayoutManager(this@ParkingSpaceListActivity)
                }
            }
        }
        thread.start()
    }
}