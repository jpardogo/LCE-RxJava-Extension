package com.jpardogo.example.data.feature

import com.jpardogo.example.data.common.di.qualifiers.Disk
import com.jpardogo.example.data.common.di.qualifiers.Memory
import com.jpardogo.example.data.common.di.qualifiers.Remote
import com.jpardogo.example.data.common.rx.singleErrorMapper
import com.jpardogo.example.data.feature.datasources.FeatureDataSource
import com.jpardogo.example.domain.feature.ExampleDomainEntity
import com.jpardogo.example.domain.feature.FeatureRepository
import com.jpardogo.example.domain.feature.GetExampleUseCase
import io.reactivex.Maybe

class FeatureRepositoryImpl(
    @Memory val memory: FeatureDataSource,
    @Disk val disk: FeatureDataSource,
    @Remote val remote: FeatureDataSource
) : FeatureRepository {

    override fun getExample(params: GetExampleUseCase.Params) = Maybe
        .concat(
            remote
                .getInfoExampleMethod(params)
                .doOnSuccess {
                    disk.saveInfoExampleMethod2(it)
                },
            disk.getInfoExampleMethod(params)
        )
        .firstOrError()
        .onErrorResumeNext(singleErrorMapper<ExampleDomainEntity>())
}