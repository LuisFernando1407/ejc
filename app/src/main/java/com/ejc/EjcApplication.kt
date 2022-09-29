package com.ejc

import android.app.Application
import com.ejc.di.ProviderDi
import com.ejc.di.network.AppNetwork
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class EjcApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EjcApplication)
            androidLogger(Level.DEBUG)

            modules(
                ProviderDi
                    .addAllModules(AppNetwork.all())
                    .build()
            )
        }
    }
}