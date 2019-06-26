package com.jpardogo.example.data.common.rx

import io.reactivex.*
import io.reactivex.functions.Function

fun <T> maybeErrorMapper() = Function<Throwable, MaybeSource<out T>> {
    Maybe.error(mapDataSourceError(it))
}

fun <T> singleErrorMapper() = Function<Throwable, SingleSource<out T>> {
    Single.error(mapDataSourceError(it))
}

fun <T> observableErrorMapper() = Function<Throwable, ObservableSource<out T>> {
    Observable.error(mapDataSourceError(it))
}

fun <T> flowableErrorMapper() = Function<Throwable, Flowable<out T>> {
    Flowable.error(mapDataSourceError(it))
}

fun completableErrorMapper() = Function<Throwable, CompletableSource> {
    Completable.error(mapDataSourceError(it))
}

//TODO
private fun mapDataSourceError(t: Throwable) = t
