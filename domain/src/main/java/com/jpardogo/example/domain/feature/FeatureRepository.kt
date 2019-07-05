package com.jpardogo.example.domain.feature

import io.reactivex.Single

interface FeatureRepository {
    fun getExample(params: GetExampleUseCase.Params): Single<ExampleDomainEntity>
}