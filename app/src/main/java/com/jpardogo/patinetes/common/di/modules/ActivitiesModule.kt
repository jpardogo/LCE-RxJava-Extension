package com.jpardogo.patinetes.common.di.modules

import com.jpardogo.patinetes.common.di.annotations.ActivityScope
import com.jpardogo.patinetes.map.MapActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [ActivityViewModelsModule::class]
)
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashActivity(): MapActivity

}