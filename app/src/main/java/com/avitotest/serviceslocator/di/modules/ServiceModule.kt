package com.avitotest.serviceslocator.di.modules

import com.avitotest.serviceslocator.entity.repository.ServiceRepository
import com.avitotest.serviceslocator.screens.map.MapViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceModule = module {
    viewModel { MapViewModel(get()) }
    single { ServiceRepository(androidApplication()) }
}
