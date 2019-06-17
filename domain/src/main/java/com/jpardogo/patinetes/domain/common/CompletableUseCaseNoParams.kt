package com.jpardogo.patinetes.domain.common

import io.reactivex.Completable

abstract class CompletableUseCaseNoParams protected constructor(
    private val threadScheduler: ThreadScheduler
) {

    protected abstract fun buildUseCaseSource(): Completable

    fun execute(): Completable = this.buildUseCaseSource()
        .subscribeOn(threadScheduler.io())
        .observeOn(threadScheduler.main())
}