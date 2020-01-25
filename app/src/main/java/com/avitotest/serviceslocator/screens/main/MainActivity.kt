package com.avitotest.serviceslocator.screens.main

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.avitotest.serviceslocator.R
import com.avitotest.serviceslocator.entity.model.Pin
import com.avitotest.serviceslocator.entity.model.Service
import com.avitotest.serviceslocator.extensions.showSingle
import com.avitotest.serviceslocator.screens.filter.FilterActivity
import com.avitotest.serviceslocator.screens.service.ServiceInfoDialog
import com.avitotest.serviceslocator.utils.ClusterRenderer
import com.avitotest.serviceslocator.utils.PinMarker
import com.avitotest.serviceslocator.view.PinMarkerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.ui.IconGenerator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val vm: MainViewModel by viewModel()
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)
    }

    private fun getMarkerIcon(pin: Pin): Bitmap = with(IconGenerator(this)) {
        setContentView(PinMarkerView(this@MainActivity).apply {
            setTitle(pin.service)
        })
        setBackground(ColorDrawable(Color.TRANSPARENT))
        return makeIcon(pin.service)
    }

    private fun getPinMarker(pin: Pin): PinMarker =
        PinMarker(MarkerOptions()
            .position(pin.getLatLng())
            .title(pin.service)
            .snippet(pin.service)
            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerIcon(pin)))
        )

    override fun onMapReady(map: GoogleMap?) {

        googleMap = map

        vm.lastCameraPosition?.let {
            googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(it))
        } ?: run {
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(55.75, 37.6167), 15f))
        }

        val clusterManager = ClusterManager<PinMarker>(this, googleMap).also {

            it.renderer = ClusterRenderer(this, googleMap, it)

            it.setOnClusterItemClickListener { marker ->
                ServiceInfoDialog().apply {
                    arguments = bundleOf(Service.SERVICE_BUNDLE_TAG to marker.title)
                }.showSingle(supportFragmentManager, ServiceInfoDialog.TAG)
                true
            }

            it.setOnClusterClickListener { cluster ->
                map?.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    cluster.position,
                    map.cameraPosition.zoom + 1
                ))
                true
            }
        }

        googleMap?.setOnCameraIdleListener(clusterManager)
        googleMap?.setOnMarkerClickListener(clusterManager)

        vm.pins.observe(this, Observer { pins ->
            clusterManager.clearItems()
            for (pin in pins) clusterManager.addItem(getPinMarker(pin))
            clusterManager.cluster()

            filterButton.setOnClickListener {
                startActivityForResult(Intent(this, FilterActivity::class.java).apply {
                    putExtra(Service.SERVICES_BUNDLE_TAG, vm.filter)
                }, FilterActivity.RESULT_APPLY_FILTER)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == FilterActivity.RESULT_APPLY_FILTER) {
            data?.getStringArrayListExtra(Service.SERVICES_BUNDLE_TAG)?.let { vm.filter = it }
        } else super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        vm.lastCameraPosition = googleMap?.cameraPosition
        super.onSaveInstanceState(outState)
    }
}
