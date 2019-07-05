package com.jpardogo.example.common.di.modules

import com.jpardogo.example.presentation.common.CrashlyticsReporter
import com.jpardogo.lce.CrashReporter
import dagger.Module
import dagger.Provides

@Module(
    includes = [AppModuleBinds::class]
)
class AppModule {

    @Provides
    fun provideCrashlyticsReporter(): CrashReporter = CrashlyticsReporter()
}