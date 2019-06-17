package com.jpardogo.patinetes.common.di.modules

import com.jpardogo.patinetes.common.initializers.AppInitializer
import com.jpardogo.patinetes.common.initializers.ThreeTenInitializer
import com.jpardogo.patinetes.common.initializers.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class PresentationModuleBinds {

    @Binds
    @IntoSet
    abstract fun provideThreeTenInitializer(bind: ThreeTenInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun provideTimberInitializer(bind: TimberInitializer): AppInitializer
}