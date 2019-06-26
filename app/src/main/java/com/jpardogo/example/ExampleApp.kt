package com.jpardogo.example

import android.content.Context
import androidx.multidex.MultiDex
import com.jpardogo.example.common.di.DaggerPresentationComponent
import com.jpardogo.example.common.initializers.AppInitializers
import com.jpardogo.example.data.common.di.DaggerDataComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class ExampleApp : DaggerApplication() {

    @Inject
    lateinit var initializers: AppInitializers

    override fun onCreate() {
        super.onCreate()
        initializers.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val presentationComponentBuilder = DaggerPresentationComponent
            .builder()
            .application(this)
        presentationComponentBuilder.dataComponent(
            DaggerDataComponent
                .builder()
                .application(this)
                .build()
        )
        return presentationComponentBuilder.build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}