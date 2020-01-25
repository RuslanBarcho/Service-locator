package com.avitotest.serviceslocator.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

// Объект с функцией, которая преобразует vector drawable в BitmapDescriptor
object SvgBitmapDescriptor {
    fun getSvgDescriptor(context: Context?, vectorId: Int): BitmapDescriptor? {
        return if (context != null) {
            ContextCompat.getDrawable(context, vectorId)?.let {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
                val bitmap = Bitmap.createBitmap(
                    it.intrinsicWidth,
                    it.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                it.draw(canvas)
                BitmapDescriptorFactory.fromBitmap(bitmap)
            }

        } else null
    }
}
