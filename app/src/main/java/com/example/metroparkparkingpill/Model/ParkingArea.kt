package com.example.metroparkparkingpill.Model

data class Data(
    val parkingAreas: List<ParkingArea>
)

data class ParkingArea(
    val allowedParkingTime: Int?,
    val allpos: List<Allpos>?,
    val areaAddress: String?,
    val areaName: String,
    val areaObservationTime: Int?,
    val markerLat: Any?,
    val markerLng: Any?,
    val parkingAreaId: Int?,
    val parkingSpace: ParkingSpace?,
    val parkingSpaceList: List<Int>?
)

data class Allpos(
    val markerlat: Int?,
    val markerlong: Int?
)

data class ParkingSpace(
    val arrivalTime: String?,
    val occupied: Any?,
    val parkingAreaId: Int?,
    val parkingSpaceId: Int?,
    val pos: Pos?
)

data class Pos(
    val markerlat: Int?,
    val markerlong: Int?
)