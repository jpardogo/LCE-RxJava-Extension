package com.jpardogo.example.data.feature.datasources

import com.jpardogo.example.data.common.database.ExampleRoomDatabase
import com.jpardogo.example.data.feature.toDomain
import com.jpardogo.example.data.feature.toRoom
import com.jpardogo.example.domain.feature.ExampleDomainEntity
import com.jpardogo.example.domain.feature.GetExampleUseCase

class FeatureDiskDataSource(val database: ExampleRoomDatabase) : FeatureDataSource {
    override fun getInfoExampleMethod(params: GetExampleUseCase.Params) =
        database.exampleDAO().getInfoExample(params.p1.toLong()).map {
            it.toDomain()
        }

    override fun saveInfoExampleMethod2(exampleDomainEntity: ExampleDomainEntity) {
        database.exampleDAO().insert(exampleDomainEntity.toRoom())
    }
}