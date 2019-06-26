package com.jpardogo.example.data.common.di.modules

import com.jpardogo.example.data.ExampleRepositoryImpl
import com.jpardogo.example.domain.ExampleRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun provideExampleRepository(): ExampleRepository = ExampleRepositoryImpl()
}