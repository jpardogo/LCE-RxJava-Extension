package com.jpardogo.patinetes.data.common.di.modules

import com.jpardogo.patinetes.data.ScootersRepositoryImpl
import com.jpardogo.patinetes.domain.ScootersRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun provideScootersRepository(): ScootersRepository = ScootersRepositoryImpl()
}