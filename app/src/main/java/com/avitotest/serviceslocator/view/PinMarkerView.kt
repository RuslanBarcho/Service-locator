package com.avitotest.serviceslocator.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.avitotest.serviceslocator.R
import kotlinx.android.synthetic.main.marker.view.*

class PinMarkerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    init {
        val inflater = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.marker, this)
    }

    fun setTitle(title: String) {
        markerText.text = title
    }
}