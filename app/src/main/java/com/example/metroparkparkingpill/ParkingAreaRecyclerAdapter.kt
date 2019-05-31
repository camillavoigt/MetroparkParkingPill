package com.example.metroparkparkingpill

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.metroparkparkingpill.Model.Data
import com.example.metroparkparkingpill.Model.ParkingArea
import kotlinx.android.synthetic.main.parking_area_list_element_view.view.*
import java.util.*

class ParkingAreaRecyclerAdapter(val data: Data) :
    RecyclerView.Adapter<ParkingAreaRecyclerAdapter.ParkingAreaHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ParkingAreaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parking_area_list_element_view, parent, false);
        return ParkingAreaHolder(
            view,
            view.areaNameTextView,
            view.areaAddressTextView,
            view.areaTotalSpaceCountTextView,
            view.areaOccupiedCountTextView,
            view.areaIllegallyParkedCarsCountTextView
        )
    }

    var onItemSelected: ((ParkingArea) -> Unit)? = null
    override fun getItemCount(): Int {
        return data.parkingAreas.size
    }

    override fun onBindViewHolder(holder: ParkingAreaRecyclerAdapter.ParkingAreaHolder, position: Int) {
        val area = data.parkingAreas[position]
        holder.areaName.text = area.areaName
        holder.areaAddress.text = area.areaAddress
        holder.totalCount.text = area.parkingSpaceList.size.toString()
        holder.occupiedCount.text = area.parkingSpaceList.count { it.occupied }.toString()

        var countIllegal = 0
        area.parkingSpaceList.forEach { space ->

            val currentTime = Date()
            val currentTimeMil = currentTime.time

            val allowedParkTimeMil = space.allowedParkingTime * 60000

            if (currentTimeMil > allowedParkTimeMil + space.arrivalTime) {
                countIllegal++
            }
        }

        holder.illegallyCount.text = countIllegal.toString()

        holder.itemView.setOnClickListener {
            val callback = onItemSelected;
            if (callback != null) {
                callback(area)
            }
        }
    }

    class ParkingAreaHolder(
        view: View,
        val areaName: TextView,
        val areaAddress: TextView,
        val totalCount: TextView,
        val occupiedCount: TextView,
        val illegallyCount: TextView
    ) : RecyclerView.ViewHolder(view)

}