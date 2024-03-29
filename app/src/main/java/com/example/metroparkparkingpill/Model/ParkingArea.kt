package com.example.metroparkparkingpill.Model

data class Data(
    val parkingAreas: List<ParkingArea>
)

data class ParkingArea(
    val position: List<Position>,
    val areaAddress: String,
    val customer: String,
    val areaName: String,
    val parkingAreaId: Int,
    val parkingSpaceList: List<ParkingSpace>
)

data class ParkingSpace(
    val observationTime: Int,
    val allowedParkingTime: Int,
    val arrivalTime: Long,
    val occupied: Boolean,
    val parkingAreaId: Int,
    val parkingSpaceName: String,
    val sensorID: String,
    val position: Position
)

data class Position(
    val markerlat: Double,
    val markerlong: Double
)