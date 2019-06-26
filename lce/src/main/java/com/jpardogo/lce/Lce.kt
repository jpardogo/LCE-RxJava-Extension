package com.jpardogo.lce

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

/*
*   Loading, Content, Error Pattern to be used in presentation logic. Common in MVI, but we're not doing that.
*   It should not be used in the services layer, as they should not be concerned with loading states and so on.
*   Lce allows the ViewModel subscriber to be agnostic to the particular kind of observable source used in the domain layer.
*/
sealed class Lce<C, E> {
    data class Loading<C, E>(val loading: Boolean) : Lce<C, E>()
    data class Content<C, E>(val content: C) : Lce<C, E>()
    data class Error<C, E>(val error: E) : Lce<C, E>()

    fun actions(
        actionContent: (C) -> Unit,
        actionError: (E) -> Unit,
        actionLoading: (Boolean) -> Unit
    ): Any =
        when (this) {
            is Content -> actionContent(content)
            is Error -> actionError(error)
            is Loading -> actionLoading(loading)
        }
}

fun <C> Single<C>.toLce(): Observable<Lce<C, Throwable>> =
    toObservable().toLce()

fun <C> Observable<C>.toLce(): Observable<Lce<C, Throwable>> =
    map { Lce.Content<C, Throwable>(it) }
        .startWith(Lce.Loading(true))
        .onErrorReturn { Lce.Error(it) }
        .concatWith(Observable.just(Lce.Loading(false)))

fun Completable.toLce(): Observable<Lce<Unit, Throwable>> =
    andThen(
        Observable.just<Lce<Unit, Throwable>>(
            Lce.Content(
                Unit
            )
        )
    )
        .startWith(Lce.Loading(true))
        .onErrorReturn { Lce.Error(it) }
        .concatWith(Observable.just(Lce.Loading(false)))

fun <C, E> Observable<Lce<C, E>>.subscribeLce(
    onContent: (C) -> Unit,
    onError: (E) -> Unit,
    onLoading: ((Boolean) -> Unit)? = null
): Disposable = subscribeBy { lce ->
    lce.actions({
        onContent(it)
    }, {
        onError(it)
    }, { value ->
        onLoading?.let {
            it(value)
        }
    })
}
