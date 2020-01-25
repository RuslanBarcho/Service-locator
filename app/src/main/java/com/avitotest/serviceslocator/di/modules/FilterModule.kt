package com.avitotest.serviceslocator.di.modules

import com.avitotest.serviceslocator.screens.filter.FilterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filterModule = module {
    viewModel { FilterViewModel(get()) }
}
