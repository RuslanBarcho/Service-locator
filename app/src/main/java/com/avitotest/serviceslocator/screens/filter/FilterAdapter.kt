package com.avitotest.serviceslocator.screens.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.avitotest.serviceslocator.R
import kotlinx.android.synthetic.main.item_service.view.*

class FilterAdapter(
    private val services: ArrayList<String>,
    private val filter: ArrayList<String>
): RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder =
        FilterViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service, parent, false))

    override fun getItemCount(): Int = services.size

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(services[position])
    }

    inner class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String) = with(itemView) {

            serviceTitle.text = context.getString(R.string.service_item, item)

            val isSelected = filter.contains(item)

            itemView.setBackgroundResource(
                if (isSelected) R.drawable.service_background_active
                else R.drawable.service_background_inactive
            )
            serviceTitle.setTextColor(
                if (isSelected) context.getColor(R.color.colorPrimary)
                else context.getColor(R.color.colorAccent)
            )
            serviceSelected.isVisible = isSelected

            itemView.setOnClickListener { onServiceTapped(item) }
        }
    }

    fun onServiceTapped(item: String) {
        if (filter.contains(item)) filter.remove(item)
        else filter.add(item)
        notifyItemChanged(services.indexOf(item))
    }
}
