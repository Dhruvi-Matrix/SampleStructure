package com.matrixhive.localizationlib

import android.app.Application
import com.facebook.stetho.Stetho
import com.matrixhive.localizationlib.framework.RetrofitService.networkModuleDi
import com.matrixhive.localizationlib.framework.databaseModule
import com.matrixhive.localizationlib.framework.repositoryModule
import com.matrixhive.localizationlib.framework.viewModelModuleDI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LocalizationApplication: Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: LocalizationApplication? = null

        fun applicationContext(): LocalizationApplication {
            return instance as LocalizationApplication
        }
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LocalizationApplication)
            modules(
                listOf(
                    networkModuleDi,
                    viewModelModuleDI,
                    databaseModule,
                    repositoryModule
                )
            )
        }
        Stetho.initializeWithDefaults(this)
        
    }
}