package com.jpardogo.example.common.di.modules

import com.jpardogo.example.common.initializers.AppInitializer
import com.jpardogo.example.common.initializers.ThreeTenInitializer
import com.jpardogo.example.common.initializers.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class AppModuleBinds {

    @Binds
    @IntoSet
    abstract fun provideThreeTenInitializer(bind: ThreeTenInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun provideTimberInitializer(bind: TimberInitializer): AppInitializer
}