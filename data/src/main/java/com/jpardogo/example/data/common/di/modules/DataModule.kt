package com.jpardogo.example.data.common.di.modules

import com.jpardogo.example.data.common.rx.RxScheduler
import com.jpardogo.example.domain.common.ThreadScheduler
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideRxScheduler(): ThreadScheduler = RxScheduler()
}