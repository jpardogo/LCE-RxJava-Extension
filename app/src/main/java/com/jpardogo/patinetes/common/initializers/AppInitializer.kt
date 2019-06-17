package com.jpardogo.patinetes.common.initializers

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}