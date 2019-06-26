package com.jpardogo.example.common.initializers

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}