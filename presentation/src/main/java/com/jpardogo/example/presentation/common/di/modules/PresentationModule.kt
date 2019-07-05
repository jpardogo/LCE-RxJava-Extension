package com.jpardogo.example.presentation.common.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module(
    includes = [ActivitiesModule::class]
)
class PresentationModule {

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()
}