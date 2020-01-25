package com.avitotest.serviceslocator.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avitotest.serviceslocator.entity.model.Pin
import com.avitotest.serviceslocator.entity.repository.ServiceRepository
import com.google.android.gms.maps.model.CameraPosition

class MainViewModel(private val repository: ServiceRepository) : ViewModel() {

    // список отображаемых точек
    var filter = ArrayList<String>()
    set(value) {
        field = value
        pins.postValue(repository.service?.pins?.filter { value.contains(it.service) })
    }

    var lastCameraPosition: CameraPosition? = null

    // сами точки, которые будут отображены на карте
    val pins =  MutableLiveData<List<Pin>>()

    init {
        repository.service?.let {
            pins.postValue(it.pins.filter { pin -> it.services.contains(pin.service) })
            filter.addAll(it.services)
        }
    }
}
