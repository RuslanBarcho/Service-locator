package com.avitotest.serviceslocator.entity.repository

import android.content.Context
import com.avitotest.serviceslocator.entity.model.Service
import com.google.gson.Gson

class ServiceRepository(context: Context) {

    var service: Service? = null

    init {
        context.assets.open("pins.json").use {
            val json = it.readBytes().toString(Charsets.UTF_8)
            service = Gson().fromJson(json, Service::class.java)
        }
    }
}
