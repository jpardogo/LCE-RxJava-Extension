package com.jpardogo.example.data.feature.datasources

import com.jpardogo.example.data.common.ExampleService
import com.jpardogo.example.data.feature.toDomain
import com.jpardogo.example.domain.feature.ExampleDomainEntity
import com.jpardogo.example.domain.feature.GetExampleUseCase
import io.reactivex.Maybe

class FeatureRemoteDataSource(private val exampleService: ExampleService) : FeatureDataSource {

    override fun getInfoExampleMethod(params: GetExampleUseCase.Params): Maybe<ExampleDomainEntity> =
        exampleService.example(params.p1.toString(), params.p2.toString()).map {
            it.toDomain()
        }
}