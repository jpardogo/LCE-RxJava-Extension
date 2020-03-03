@file:Suppress("unused")

package com.jpardogo.lce

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jpardogo.lce.ThreadStrategy.DefaultThread
import com.jpardogo.lce.ThreadStrategy.ForceMainThread

fun <T> MutableLiveData<T>.asImmutable() = this as LiveData<T>
fun <T> MutableSingleLiveEvent<T>.asImmutable() = this as SingleLiveEvent<T>

fun MutableLceSingleLiveEventCompletable.asImmutable() = this as LceSingleLiveEvent<Unit>
fun <C> MutableLceSingleLiveEvent<C>.asImmutable() = this as LceSingleLiveEvent<C>
fun <C, E> MutableLceSingleLiveEvent2<C, E>.asImmutable() = this as LceSingleLiveEvent2<C, E>
fun <L, C, E> MutableLceSingleLiveEvent3<L, C, E>.asImmutable() =
    this as LceSingleLiveEvent3<L, C, E>

class MutableLceSingleLiveEventCompletable(crashReporter: CrashReporter? = null) :
    LceSingleLiveEvent<Unit>(crashReporter) {

    fun isLoading(isLoading: Boolean, threadStrategy: ThreadStrategy = DefaultThread) {
        super.loading(isLoading, threadStrategy)
    }

    fun complete(threadStrategy: ThreadStrategy = DefaultThread) {
        super.complete(Unit, threadStrategy)
    }

    public override fun error(
        error: LceErrorViewEntity,
        throwable: Throwable?,
        threadStrategy: ThreadStrategy
    ) {
        super.error(error, throwable, threadStrategy)
    }
}

class MutableLceSingleLiveEvent<C>(crashReporter: CrashReporter? = null) :
    LceSingleLiveEvent<C>(crashReporter) {

    fun isLoading(isLoading: Boolean, threadStrategy: ThreadStrategy = DefaultThread) {
        super.loading(isLoading, threadStrategy)
    }

    public override fun content(content: C, threadStrategy: ThreadStrategy) {
        super.content(content, threadStrategy)
    }

    public override fun error(
        error: LceErrorViewEntity,
        throwable: Throwable?,
        threadStrategy: ThreadStrategy
    ) {
        super.error(error, throwable, threadStrategy)
    }
}

class MutableLceSingleLiveEvent2<C, E>(crashReporter: CrashReporter? = null) :
    LceSingleLiveEvent2<C, E>(crashReporter) {
    fun isLoading(isLoading: Boolean, threadStrategy: ThreadStrategy = DefaultThread) {
        super.loading(isLoading, threadStrategy)
    }

    public override fun content(content: C, threadStrategy: ThreadStrategy) {
        super.content(content, threadStrategy)
    }

    public override fun error(
        error: E,
        throwable: Throwable?,
        threadStrategy: ThreadStrategy
    ) {
        super.error(error, throwable, threadStrategy)
    }
}

class MutableLceSingleLiveEvent3<L, C, E>(crashReporter: CrashReporter? = null) :
    LceSingleLiveEvent3<L, C, E>(crashReporter) {

    fun isLoading(isLoading: L, threadStrategy: ThreadStrategy = DefaultThread) {
        super.loading(isLoading, threadStrategy)
    }

    public override fun content(content: C, threadStrategy: ThreadStrategy) {
        super.content(content, threadStrategy)
    }

    public override fun error(
        error: E,
        throwable: Throwable?,
        threadStrategy: ThreadStrategy
    ) {
        super.error(error, throwable, threadStrategy)
    }
}

open class LceSingleLiveEvent<C>(crashReporter: CrashReporter? = null) :
    LceSingleLiveEvent2<C, LceErrorViewEntity>(crashReporter)

open class LceSingleLiveEvent2<C, E>(crashReporter: CrashReporter?) :
    LceSingleLiveEvent3<Boolean, C, E>(crashReporter)

open class LceSingleLiveEvent3<L, C, E>(
    private val crashReporter: CrashReporter? = null,
    @VisibleForTesting(otherwise = PRIVATE) val loadingLiveEvent: MutableSingleLiveEvent<L> = MutableSingleLiveEvent(),
    @VisibleForTesting(otherwise = PRIVATE) val contentLiveEvent: MutableSingleLiveEvent<C> = MutableSingleLiveEvent(),
    @VisibleForTesting(otherwise = PRIVATE) val errorLiveEvent: MutableSingleLiveEvent<E> = MutableSingleLiveEvent()
) {

    protected open fun loading(isLoading: L, threadStrategy: ThreadStrategy = DefaultThread) {
        send(loadingLiveEvent, isLoading, threadStrategy)
    }

    protected open fun content(
        content: C,
        threadStrategy: ThreadStrategy = DefaultThread
    ) {
        send(this.contentLiveEvent, content, threadStrategy)
    }

    protected open fun error(
        error: E,
        throwable: Throwable? = null,
        threadStrategy: ThreadStrategy = DefaultThread
    ) {
        crashReporter?.report(throwable)
        send(this.errorLiveEvent, error, threadStrategy)
    }

    protected open fun complete(
        completeEvent: C,
        threadStrategy: ThreadStrategy = DefaultThread
    ) {
        send(contentLiveEvent, completeEvent, threadStrategy)
    }

    protected open fun <T> send(
        liveEvent: MutableSingleLiveEvent<T>,
        data: T?,
        threadStrategy: ThreadStrategy
    ) {
        liveEvent.run {
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
        contentLiveEvent.observe(lifecycleOwner, Observer { content ->
            onContent(content)
        })

        errorLiveEvent.observe(lifecycleOwner, Observer { error ->
            onError(error)
        })

        onLoading?.let {
            loadingLiveEvent.observe(lifecycleOwner, Observer { loadingLiveEvent ->
                it(loadingLiveEvent)
            })
        }
    }
}