package com.jpardogo.example.data.feature.datasources

import com.jpardogo.example.data.common.IllegalDataSourceException
import com.jpardogo.example.domain.feature.ExampleDomainEntity
import com.jpardogo.example.domain.feature.GetExampleUseCase
import io.reactivex.Maybe

interface FeatureDataSource {

    fun getInfoExampleMethod(params: GetExampleUseCase.Params): Maybe<ExampleDomainEntity>

    fun saveInfoExampleMethod2(exampleDomainEntity: ExampleDomainEntity) {
        throw IllegalDataSourceException()
    }
}