package com.jpardogo.patinetes.common.initializers

import android.app.Application
import com.jpardogo.patinetes.BuildConfig
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}