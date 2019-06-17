package com.jpardogo.patinetes.domain.common

import io.reactivex.Observable

abstract class ObservableUseCaseNoParams<Output> protected constructor(
    private val threadScheduler: ThreadScheduler
) {

    protected abstract fun buildUseCaseObservable(): Observable<Output>

    fun execute(): Observable<Output> = this.buildUseCaseObservable()
        .subscribeOn(threadScheduler.io())
        .observeOn(threadScheduler.main())
}