package com.example.metroparkparkingpill

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.metroparkparkingpill.Model.ParkingSpace
import kotlinx.android.synthetic.main.parking_space_list_element_view.view.*
import java.time.Instant
import java.time.ZoneId.systemDefault
import java.time.Instant.ofEpochMilli
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneId.of


class ParkingSpaceRecyclerAdapter(val parkingSpaces: List<ParkingSpace>) :
    RecyclerView.Adapter<ParkingSpaceRecyclerAdapter.ParkingSpaceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ParkingSpaceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parking_space_list_element_view, parent, false)
        return ParkingSpaceHolder(
            view,
            view.spaceNameTextView,
            view.observationTimeTextView,
            view.spaceOccupiedCountTextView,
            view.arrivalTimeTextView,
            view.parkingTimeTextView
        )
    }

    override fun getItemCount(): Int {
        return parkingSpaces.size
    }

    override fun onBindViewHolder(holder: ParkingSpaceRecyclerAdapter.ParkingSpaceHolder, position: Int) {
        val parkingSpace = parkingSpaces[position]
        val date: String

        if (parkingSpace.arrivalTime < 1) {
            date = ""
        } else {
            date = Instant.ofEpochMilli(parkingSpace.arrivalTime)
                .atZone(ZoneId.systemDefault())
                .toLocalTime().toString()
        }

        val currentDate = LocalDateTime.now()
            .atZone(ZoneId.systemDefault())
            .toEpochSecond()
        val time = parkingSpace.arrivalTime / 1000
        val stringTime = (currentDate - time)
        val parkingTime: String


        if (parkingSpace.arrivalTime < 1) {
            parkingTime = ""
        } else {
            parkingTime = Instant.ofEpochSecond(stringTime).atZone(ZoneId.of("UTC")).toLocalTime().toString()
        }

        holder.spaceName.text = parkingSpace.parkingSpaceName
        holder.observationTime.text = parkingSpace.observationTime.toString()
        holder.occupied.text = parkingSpace.occupied.toString()
        holder.arrivalTime.text = date
        holder.parkingTime.text = parkingTime
    }

    class ParkingSpaceHolder(
        view: View,
        val spaceName: TextView,
        val observationTime: TextView,
        val occupied: TextView,
        val arrivalTime: TextView,
        val parkingTime: TextView
    ) : RecyclerView.ViewHolder(view)
}