package com.jpardogo.example.data.common.di.modules

import android.content.SharedPreferences
import com.jpardogo.example.data.common.ExampleService
import com.jpardogo.example.data.common.database.ExampleRoomDatabase
import com.jpardogo.example.data.common.datasources.SharedPrefDataSource
import com.jpardogo.example.data.common.datasources.SharedPrefDataSourceImpl
import com.jpardogo.example.data.common.di.annotations.DataScope
import com.jpardogo.example.data.common.di.qualifiers.Disk
import com.jpardogo.example.data.common.di.qualifiers.Memory
import com.jpardogo.example.data.common.di.qualifiers.Remote
import com.jpardogo.example.data.feature.datasources.FeatureDataSource
import com.jpardogo.example.data.feature.datasources.FeatureDiskDataSource
import com.jpardogo.example.data.feature.datasources.FeatureMemoryDataSource
import com.jpardogo.example.data.feature.datasources.FeatureRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataSourcesModule {

    @Provides
    fun provideSharedPrefDataSource(sharedPreferences: SharedPreferences): SharedPrefDataSource =
        SharedPrefDataSourceImpl(sharedPreferences)

    //Feature
    @Provides
    @Memory
    @DataScope
    fun provideFeatureMemoryDataSource(): FeatureDataSource = FeatureMemoryDataSource()

    @Provides
    @Disk
    @DataScope
    fun provideFeatureDiskDataSource(exampleRoomDatabase: ExampleRoomDatabase): FeatureDataSource =
        FeatureDiskDataSource(exampleRoomDatabase)

    @Provides
    @Remote
    @DataScope
    fun provideFeatureRemoteDataSource(exampleService: ExampleService): FeatureDataSource =
        FeatureRemoteDataSource(exampleService)
}