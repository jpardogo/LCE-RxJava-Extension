package com.jpardogo.example.data.common.di.modules

import com.jpardogo.example.data.common.di.qualifiers.Disk
import com.jpardogo.example.data.common.di.qualifiers.Memory
import com.jpardogo.example.data.common.di.qualifiers.Remote
import com.jpardogo.example.data.feature.FeatureRepositoryImpl
import com.jpardogo.example.data.feature.datasources.FeatureDataSource
import com.jpardogo.example.domain.feature.FeatureRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun provideExampleRepository(
        @Memory memory: FeatureDataSource,
        @Disk disk: FeatureDataSource,
        @Remote remote: FeatureDataSource
    ): FeatureRepository =
        FeatureRepositoryImpl(memory, disk, remote)
}