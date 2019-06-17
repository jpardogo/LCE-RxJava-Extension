package com.jpardogo.patinetes.data.common.di.modules

import android.content.SharedPreferences
import com.jpardogo.patinetes.data.common.datasources.SharedPrefDataSource
import com.jpardogo.patinetes.data.common.datasources.SharedPrefDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataSourcesModule {

    @Provides
    fun provideSharedPrefDataSource(sharedPreferences: SharedPreferences): SharedPrefDataSource =
        SharedPrefDataSourceImpl(sharedPreferences)
}