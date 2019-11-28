package com.jpardogo.lce

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

fun MutableLceLiveDataCompletable.asImmutable() = this as LceLiveData<Unit>
fun <C> MutableLceLiveData<C>.asImmutable() = this as LceLiveData<C>
fun <C, E> MutableLceLiveData2<C, E>.asImmutable() = this as LceLiveData2<C, E>
fun <L, C, E> MutableLceLiveData3<L, C, E>.asImmutable() = this as LceLiveData3<L, C, E>

class MutableLceLiveDataCompletable(crashReporter: CrashReporter? = null) :
    LceLiveData<Unit>(crashReporter) {

    fun isLoading(isLoading: Boolean, threadStrategy: LiveDataThreadStrategy = DefaultThread) {
        super.loading(isLoading, threadStrategy)
    }

    fun complete(threadStrategy: LiveDataThreadStrategy = DefaultThread) {
        super.complete(Unit, threadStrategy)
    }

    public override fun error(
        error: LceErrorViewEntity,
        throwable: Throwable?,
        threadStrategy: LiveDataThreadStrategy
    ) {
        super.error(error, throwable, threadStrategy)
    }
}

class MutableLceLiveData<C>(crashReporter: CrashReporter? = null) : LceLiveData<C>(crashReporter) {

    fun isLoading(isLoading: Boolean, threadStrategy: LiveDataThreadStrategy = DefaultThread) {
        super.loading(isLoading, threadStrategy)
    }

    public override fun content(content: C, threadStrategy: LiveDataThreadStrategy) {
        super.content(content, threadStrategy)
    }

    public override fun error(
        error: LceErrorViewEntity,
        throwable: Throwable?,
        threadStrategy: LiveDataThreadStrategy
    ) {
        super.error(error, throwable, threadStrategy)
    }
}

class MutableLceLiveData2<C, E>(crashReporter: CrashReporter? = null) :
    LceLiveData2<C, E>(crashReporter) {
    fun isLoading(isLoading: Boolean, threadStrategy: LiveDataThreadStrategy = DefaultThread) {
        super.loading(isLoading, threadStrategy)
    }

    public override fun content(content: C, threadStrategy: LiveDataThreadStrategy) {
        super.content(content, threadStrategy)
    }

    public override fun error(
        error: E,
        throwable: Throwable?,
        threadStrategy: LiveDataThreadStrategy
    ) {
        super.error(error, throwable, threadStrategy)
    }
}

class MutableLceLiveData3<L, C, E>(crashReporter: CrashReporter? = null) :
    LceLiveData3<L, C, E>(crashReporter) {

    fun isLoading(isLoading: L, threadStrategy: LiveDataThreadStrategy = DefaultThread) {
        super.loading(isLoading, threadStrategy)
    }

    public override fun content(content: C, threadStrategy: LiveDataThreadStrategy) {
        super.content(content, threadStrategy)
    }

    public override fun error(
        error: E,
        throwable: Throwable?,
        threadStrategy: LiveDataThreadStrategy
    ) {
        super.error(error, throwable, threadStrategy)
    }
}

open class LceLiveData<C>(crashReporter: CrashReporter? = null) :
    LceLiveData2<C, LceErrorViewEntity>(crashReporter)

open class LceLiveData2<C, E>(crashReporter: CrashReporter?) :
    LceLiveData3<Boolean, C, E>(crashReporter)

open class LceLiveData3<L, C, E>(
    private val crashReporter: CrashReporter? = null,
    @VisibleForTesting(otherwise = PRIVATE) val loadingLiveData: SingleMutableLiveEvent<L> = SingleMutableLiveEvent(),
    @VisibleForTesting(otherwise = PRIVATE) val contentLiveData: SingleMutableLiveEvent<C> = SingleMutableLiveEvent(),
    @VisibleForTesting(otherwise = PRIVATE) val errorLiveData: SingleMutableLiveEvent<E> = SingleMutableLiveEvent()
) {

    /**
     * FIXME https://youtrack.jetbrains.com/issue/KT-31946
     * = DefaultThread cannot be added as a default parameter until the above kotlin bug is resolved
     *
     */
    protected open fun loading(isLoading: L, threadStrategy: LiveDataThreadStrategy) {
        send(loadingLiveData, isLoading, threadStrategy)
    }

    protected open fun content(content: C, threadStrategy: LiveDataThreadStrategy = DefaultThread) {
        send(this.contentLiveData, content, threadStrategy)
    }

    protected open fun error(
        error: E,
        throwable: Throwable? = null,
        threadStrategy: LiveDataThreadStrategy = DefaultThread
    ) {
        crashReporter?.report(throwable)
        send(this.errorLiveData, error, threadStrategy)
    }

    protected open fun complete(
        completeEvent: C,
        threadStrategy: LiveDataThreadStrategy = DefaultThread
    ) {
        send(contentLiveData, completeEvent, threadStrategy)
    }

    protected open fun <T> send(
        liveData: SingleMutableLiveEvent<T>,
        data: T?,
        threadStrategy: LiveDataThreadStrategy = DefaultThread
    ) {
        liveData.run {
            when (threadStrategy) {
                is ForceMainThread -> postValue(data)
                is DefaultThread -> value = data
            }
        }
    }

    fun observeLce(
        lifecycleOwner: LifecycleOwner,
        onContent: (C) -> Unit,
        onError: (E) -> Unit,
        onLoading: ((L) -> Unit)? = null
    ) {
        contentLiveData.observe(lifecycleOwner, Observer { content ->
            onContent(content)
        })

        errorLiveData.observe(lifecycleOwner, Observer { error ->
            onError(error)
        })

        onLoading?.let {
            loadingLiveData.observe(lifecycleOwner, Observer { loadingLiveData ->
                it(loadingLiveData)
            })
        }
    }
}

sealed class LiveDataThreadStrategy
object ForceMainThread : LiveDataThreadStrategy()
object DefaultThread : LiveDataThreadStrategy()