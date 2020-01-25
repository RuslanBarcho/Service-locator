package com.avitotest.serviceslocator.screens.filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import com.avitotest.serviceslocator.R
import com.avitotest.serviceslocator.entity.model.Service
import kotlinx.android.synthetic.main.activity_filter.*
import org.koin.android.viewmodel.ext.android.viewModel

class FilterActivity : AppCompatActivity() {

    private val vm: FilterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        if (vm.filter == null) vm.filter =
            intent.getStringArrayListExtra(Service.SERVICES_BUNDLE_TAG)

        vm.services.observe(this, Observer { services ->
            with(filterServices) {
                layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down)
                vm.filter?.let { adapter = FilterAdapter(services, it) }
            }
        })

        filterBack.setOnClickListener { finish() }

        filterApply.setOnClickListener {
            setResult(RESULT_APPLY_FILTER, Intent().apply {
                putExtra(Service.SERVICES_BUNDLE_TAG, vm.filter)
            })
            finish()
        }
    }

    companion object {
        const val RESULT_APPLY_FILTER = 22
    }
}
