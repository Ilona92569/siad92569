package com.example.feature.ui.exchange.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.data.model.CityData
import com.example.feature.databinding.ItemExchangeBinding

class RateAdapter(
    private val context: Context
) : ListAdapter<CityData, RateViewHolder>(DIFF_UTIL) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val view = ItemExchangeBinding.inflate(LayoutInflater.from(context))
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.root.layoutParams = layoutParams
        return RateViewHolder(view)
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<CityData>() {
            override fun areItemsTheSame(oldItem: CityData, newItem: CityData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CityData, newItem: CityData): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}