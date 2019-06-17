package com.jpardogo.patinetes.data.common.di.modules

import com.jpardogo.patinetes.data.common.rx.RxScheduler
import com.jpardogo.patinetes.domain.common.ThreadScheduler
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideRxScheduler(): ThreadScheduler = RxScheduler()
}