package com.jpardogo.patinetes.common.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module(
    includes = [PresentationModuleBinds::class]
)
class PresentationModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}