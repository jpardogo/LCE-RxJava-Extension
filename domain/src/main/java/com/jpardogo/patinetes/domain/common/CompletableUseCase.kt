package com.jpardogo.patinetes.domain.common

import io.reactivex.Completable

abstract class CompletableUseCase<in Params> protected constructor(
    private val threadScheduler: ThreadScheduler
) {

    protected abstract fun buildUseCaseSource(params: Params): Completable

    fun execute(params: Params): Completable = buildUseCaseSource(params)
        .subscribeOn(threadScheduler.io())
        .observeOn(threadScheduler.main())
}