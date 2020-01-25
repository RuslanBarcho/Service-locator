package com.avitotest.serviceslocator.screens.filter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avitotest.serviceslocator.entity.repository.ServiceRepository

class FilterViewModel(repository: ServiceRepository) : ViewModel() {

    var filter: ArrayList<String>? = null
    var services = MutableLiveData<ArrayList<String>>()

    init {
        repository.service?.let {
            services.postValue(it.services)
        }
    }
}
