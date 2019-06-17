package com.jpardogo.patinetes.domain.common

import io.reactivex.Maybe

abstract class MaybeUseCaseNoParams<Output> protected constructor(
    private val threadScheduler: ThreadScheduler
) {

    protected abstract fun buildUseCaseSource(): Maybe<Output>

    fun execute(): Maybe<Output> = buildUseCaseSource()
        .subscribeOn(threadScheduler.io())
        .observeOn(threadScheduler.main())
}