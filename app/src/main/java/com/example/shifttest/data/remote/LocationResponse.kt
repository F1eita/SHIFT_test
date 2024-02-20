package com.example.shifttest.data.remote

data class LocationResponse(
    val street: StreetResponse,
    val city: String,
    val state: String,
    val country: String,
    val coordinates: CoordinatesResponse
)

data class StreetResponse(
    val number: Int,
    val name: String,
)

data class CoordinatesResponse(
    val latitude: String,
    val longitude: String
)