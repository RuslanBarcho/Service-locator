package com.avitotest.serviceslocator.screens.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avitotest.serviceslocator.R
import com.avitotest.serviceslocator.entity.model.Service
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_service_info_dialog.*

class ServiceInfoDialog : BottomSheetDialogFragment() {

    override fun onStart() {
        super.onStart()
        view?.post { initCoordinatorBehavior() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_service_info_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service = arguments?.getString(Service.SERVICE_BUNDLE_TAG)
        serviceInfoTitle.text = getString(R.string.title_service_info, service)

        serviceInfoOk.setOnClickListener { dismiss() }
    }

    private fun initCoordinatorBehavior() {
        (view?.parent as? View)?.let { parent ->
            try {
                BottomSheetBehavior.from(parent).run {
                    skipCollapsed = true
                    state = BottomSheetBehavior.STATE_EXPANDED
                }
            } catch (exception: IllegalArgumentException) {
                exception.printStackTrace()
            }
        }
    }

    companion object {
        const val TAG = "service_info_dialog"
    }
}
