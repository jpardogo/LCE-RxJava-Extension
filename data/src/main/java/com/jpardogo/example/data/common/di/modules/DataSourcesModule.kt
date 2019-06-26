package com.jpardogo.example.data.common.di.modules

import android.content.SharedPreferences
import com.jpardogo.example.data.common.datasources.SharedPrefDataSource
import com.jpardogo.example.data.common.datasources.SharedPrefDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataSourcesModule {

    @Provides
    fun provideSharedPrefDataSource(sharedPreferences: SharedPreferences): SharedPrefDataSource =
        SharedPrefDataSourceImpl(sharedPreferences)
}