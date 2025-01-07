package com.example.data.di

import org.koin.dsl.module
import com.example.data.repository.ExchangeRepository
import org.koin.core.module.dsl.singleOf

val repositoryModule = module {
    singleOf(::ExchangeRepository)
}