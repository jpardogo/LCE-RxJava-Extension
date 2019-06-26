package com.jpardogo.example.domain

import io.reactivex.Single

interface ExampleRepository {
    fun getExample(params: GetExampleUseCase.Params): Single<Boolean>
}