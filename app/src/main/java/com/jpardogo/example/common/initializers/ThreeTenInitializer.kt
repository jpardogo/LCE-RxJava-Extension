package com.jpardogo.example.common.initializers

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import javax.inject.Inject

class ThreeTenInitializer @Inject constructor() : AppInitializer {
    override fun init(application: Application) {
        AndroidThreeTen.init(application)
    }
}