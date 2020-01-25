package com.avitotest.serviceslocator.entity.model

import com.google.android.gms.maps.model.LatLng

data class Pin(
    val coordinates: Coordinates,
    val id: Int,
    val service: String
) {
    fun getLatLng() = LatLng(coordinates.lat, coordinates.lng)
}
