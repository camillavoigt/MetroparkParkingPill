package com.example.metroparkparkingpill

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.metroparkparkingpill.Model.ParkingArea
import com.example.metroparkparkingpill.Model.ParkingSpace
import kotlinx.android.synthetic.main.parking_space_list_element_view.view.*

class ParkingSpaceRecyclerAdapter(val parkingSpaces: List<ParkingSpace>) :
    RecyclerView.Adapter<ParkingSpaceRecyclerAdapter.ParkingSpaceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ParkingSpaceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parking_space_list_element_view, parent, false)
        return ParkingSpaceHolder(
            view,
            view.spaceIdTextView,
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
        holder.spaceId.text = parkingSpace.parkingSpaceId.toString()
        holder.observationTime.text = parkingSpace.observationTime.toString()
        holder.occupied.text = parkingSpace.occupied.toString()
        holder.arrivalTime.text = parkingSpace.arrivalTime
        holder.parkingTime.text = parkingSpace.allowedParkingTime.toString()
    }

    class ParkingSpaceHolder(
        view: View,
        val spaceId: TextView,
        val observationTime: TextView,
        val occupied: TextView,
        val arrivalTime: TextView,
        val parkingTime: TextView
    ) : RecyclerView.ViewHolder(view)
}