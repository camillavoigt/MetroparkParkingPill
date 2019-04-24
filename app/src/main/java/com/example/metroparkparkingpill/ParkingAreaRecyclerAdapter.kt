package com.example.metroparkparkingpill

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.metroparkparkingpill.Model.Data
import kotlinx.android.synthetic.main.parking_area_list_element_view.view.*

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

    override fun getItemCount(): Int {
        return data.parkingAreas.size
    }

    override fun onBindViewHolder(holder: ParkingAreaRecyclerAdapter.ParkingAreaHolder, position: Int) {
        val area = data.parkingAreas[position]
        holder.areaName.text = area.areaName
        holder.areaAddress.text = area.areaAddress
        holder.totalCount.text = area.parkingSpaceList.size.toString()
        holder.occupiedCount.text = area.parkingSpaceList.count { it.occupied }.toString()
        holder.illegallyCount.text = "2" //TODO
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