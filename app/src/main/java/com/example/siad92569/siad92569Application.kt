package com.example.siad92569

import android.app.Application
import com.example.data.di.repositoryModule
import com.example.data.di.apiModule
import com.example.feature.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class siad92569Application : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@siad92569Application)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    apiModule
                )
            )
        }
    }
}