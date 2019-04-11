package com.example.metroparkparkingpill

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.element_view.view.*

class ParkingSpace() : RecyclerView.Adapter<ParkingSpace.ParkingSpaceHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ParkingSpaceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_view, parent, false);


        return ParkingSpaceHolder(view, view.spaceInfo)
    }

    override fun getItemCount(): Int {
        return 1
    }


    override fun onBindViewHolder(holder: ParkingSpace.ParkingSpaceHolder, position: Int) {
        var test = "test"
        holder.text.spaceInfo.text = test
    }


    class ParkingSpaceHolder(view: View, val text: TextView) : RecyclerView.ViewHolder(view)

}