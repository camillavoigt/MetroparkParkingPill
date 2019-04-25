package com.example.metroparkparkingpill

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_parking_area_list.*

class ParkingAreaListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_area_list)

        val thread = Thread {
            val data = DataStorage.getInstance(this).getData()

            runOnUiThread {
                val adapter = ParkingAreaRecyclerAdapter(data)

                recyclerView.apply {
                    this.adapter = adapter
                    layoutManager = LinearLayoutManager(this@ParkingAreaListActivity)
                }

                adapter.onItemSelected = {
                    println("Area selected: " + it + "h");
                    val intent = Intent(this, ParkingSpaceListActivity::class.java)
                    intent.putExtra("id", it.parkingAreaId)
                    startActivity(intent)
                }
            }
        }
        thread.start()
    }
}