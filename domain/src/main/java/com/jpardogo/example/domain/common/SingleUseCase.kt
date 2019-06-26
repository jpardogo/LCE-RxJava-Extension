package com.jpardogo.example.domain.common

import io.reactivex.Single

abstract class SingleUseCase<in Params, T> protected constructor(
    private val threadScheduler: ThreadScheduler
) {

    protected abstract fun buildUseCaseSource(params: Params): Single<T>

    fun execute(params: Params): Single<T> = buildUseCaseSource(params)
        .subscribeOn(threadScheduler.io())
        .observeOn(threadScheduler.main())
}