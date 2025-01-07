package com.example.feature.di

import org.koin.dsl.module
import com.example.feature.ui.exchange.ExchangeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf

val viewModelModule = module {
    viewModelOf(::ExchangeViewModel)
}