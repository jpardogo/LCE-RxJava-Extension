package com.jpardogo.example.data

import com.jpardogo.example.domain.ExampleRepository
import com.jpardogo.example.domain.GetExampleUseCase
import io.reactivex.Single

//TODO Inject data source and call a method of it passing params and returning domain entity
class ExampleRepositoryImpl : ExampleRepository {
    override fun getExample(params: GetExampleUseCase.Params): Single<Boolean> = Single.just(true)
}