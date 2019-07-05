package com.jpardogo.example.data.feature.datasources

import com.jpardogo.example.domain.feature.ExampleDomainEntity
import com.jpardogo.example.domain.feature.GetExampleUseCase
import io.reactivex.Maybe

class FeatureMemoryDataSource : FeatureDataSource {

    private var exampleDomainEntity: ExampleDomainEntity? = null

    override fun getInfoExampleMethod(params: GetExampleUseCase.Params): Maybe<ExampleDomainEntity> =
        if (exampleDomainEntity == null) Maybe.empty() else Maybe.just(exampleDomainEntity)

    override fun saveInfoExampleMethod2(exampleDomainEntity: ExampleDomainEntity) {
        this.exampleDomainEntity = exampleDomainEntity
    }

}