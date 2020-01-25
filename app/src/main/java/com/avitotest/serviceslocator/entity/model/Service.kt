package com.avitotest.serviceslocator.entity.model

data class Service(
    val services: ArrayList<String>,
    val pins: ArrayList<Pin>
) {
    companion object {
        const val SERVICES_BUNDLE_TAG = "services"
        const val SERVICE_BUNDLE_TAG = "service"
    }
}
