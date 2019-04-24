package com.example.metroparkparkingpill.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class Data(
    val parkingAreas: List<ParkingArea>
)

data class ParkingArea(
    val position: List<Position>,
    val areaAddress: String,
    val areaName: String,
    val parkingAreaId: Int,
    val parkingSpaceList: List<ParkingSpace>
)

//@JsonIgnoreProperties
data class ParkingSpace(
    val observationTime: Int,
    val allowedParkingTime: Int,
    val arrivalTime: Long,
    val occupied: Boolean,
    val parkingAreaId: Int,
    val parkingSpaceId: Int,
    val sensorID: String,
    val position: Position
)

data class Position(
    val markerlat: Int,
    val markerlong: Int
)