package com.avitotest.serviceslocator.utils

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterItem

class PinMarker constructor(
    val marker: MarkerOptions
) : ClusterItem {
    override fun getSnippet(): String = marker.snippet

    override fun getTitle(): String = marker.title

    override fun getPosition(): LatLng = marker.position
}
