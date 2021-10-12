package com.sphtechapp.sphtestapp

import android.app.Application
import com.sphtechapp.sphtestapp.di.apiModule
import com.sphtechapp.sphtestapp.di.databaseModule
import com.sphtechapp.sphtestapp.di.datastoreModule
import com.sphtechapp.sphtestapp.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DatastoreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DatastoreApp)
            androidFileProperties()
            modules(provideModules())
        }
    }

    private fun provideModules() = listOf(retrofitModule, apiModule, databaseModule, datastoreModule)
}