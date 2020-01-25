package com.avitotest.serviceslocator.utils

import android.content.Context
import com.avitotest.serviceslocator.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class ClusterRenderer(
    private val context: Context,
    map: GoogleMap?,
    clusterManager: ClusterManager<PinMarker>
): DefaultClusterRenderer<PinMarker>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(item: PinMarker?, markerOptions: MarkerOptions?) {
        markerOptions?.icon(item?.marker?.icon)
    }

    override fun onBeforeClusterRendered(cluster: Cluster<PinMarker>?, markerOptions: MarkerOptions?) {
        markerOptions?.icon(SvgBitmapDescriptor.getSvgDescriptor(context, R.drawable.ic_cluster))
    }
}
