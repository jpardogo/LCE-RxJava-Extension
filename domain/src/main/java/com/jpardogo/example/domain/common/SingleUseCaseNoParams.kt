package com.jpardogo.example.domain.common

import io.reactivex.Single

abstract class SingleUseCaseNoParams<Output> protected constructor(
    private val threadScheduler: ThreadScheduler
) {

    protected abstract fun buildUseCaseSource(): Single<Output>

    fun execute(): Single<Output> = buildUseCaseSource()
        .subscribeOn(threadScheduler.io())
        .observeOn(threadScheduler.main())
}