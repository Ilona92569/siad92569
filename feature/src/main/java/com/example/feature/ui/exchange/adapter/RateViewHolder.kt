package com.example.feature.ui.exchange.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.CityData
import com.example.feature.databinding.ItemExchangeBinding

class RateViewHolder (
    private val binding: ItemExchangeBinding
) :RecyclerView.ViewHolder(binding.root) {

    fun bind(cityData: CityData) {
        with(binding) {
            textStreet.text = "${cityData.streetType} ${cityData.street} ${cityData.homeNumber}"
            usdPass.text = cityData.USDIn
            usdBuy.text = cityData.USDOut
            eurPass.text = cityData.EURIn
            eurBuy.text = cityData.EUROut
            rubPass.text = cityData.RUBIn
            rubBuy.text = cityData.RUBOut
        }
    }
}