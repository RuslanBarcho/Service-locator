package com.avitotest.serviceslocator.di

import com.avitotest.serviceslocator.di.modules.filterModule
import com.avitotest.serviceslocator.di.modules.serviceModule

val modules = mutableListOf(
    serviceModule,
    filterModule
)
