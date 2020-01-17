@file:Suppress("unused")

package com.jpardogo.lce

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jpardogo.lce.ThreadStrategy.DefaultThread
import com.jpardogo.lce.ThreadStrategy.ForceMainThread

fun MutableLceLiveDataCompletable.asImmutable() = this as LceLiveData<Unit>
fun <C> MutableLceLiveData<C>.asImmutable() = this as LceLiveData<C>
fun <C, E> MutableLceLiveData2<C, E>.asImmutable() = this as LceLiveData2<C, E>
fun <L, C, E> MutableLceLiveData3<L, C, E>.asImmutable() = this as LceLiveData3<L, C, E>

class MutableLceLiveDataCompletable(crashReporter: CrashReporter? = null) :
    LceLiveData<Unit>(crashReporter) {

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

class MutableLceLiveData<C>(crashReporter: CrashReporter? = null) : LceLiveData<C>(crashReporter) {

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

class MutableLceLiveData2<C, E>(crashReporter: CrashReporter? = null) :
    LceLiveData2<C, E>(crashReporter) {
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

class MutableLceLiveData3<L, C, E>(crashReporter: CrashReporter? = null) :
    LceLiveData3<L, C, E>(crashReporter) {

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

open class LceLiveData<C>(crashReporter: CrashReporter? = null) :
    LceLiveData2<C, LceErrorViewEntity>(crashReporter)

open class LceLiveData2<C, E>(crashReporter: CrashReporter?) :
    LceLiveData3<Boolean, C, E>(crashReporter)

open class LceLiveData3<L, C, E>(
    private val crashReporter: CrashReporter? = null,
    @VisibleForTesting(otherwise = PRIVATE) val loadingLiveData: MutableLiveData<L> = MutableLiveData(),
    @VisibleForTesting(otherwise = PRIVATE) val contentLiveData: MutableLiveData<C> = MutableLiveData(),
    @VisibleForTesting(otherwise = PRIVATE) val errorLiveData: MutableLiveData<E> = MutableLiveData()
) {

    protected open fun loading(isLoading: L, threadStrategy: ThreadStrategy = DefaultThread) {
        send(loadingLiveData, isLoading, threadStrategy)
    }

    protected open fun content(content: C, threadStrategy: ThreadStrategy = DefaultThread) {
        send(this.contentLiveData, content, threadStrategy)
    }

    protected open fun error(
        error: E,
        throwable: Throwable? = null,
        threadStrategy: ThreadStrategy = DefaultThread
    ) {
        crashReporter?.report(throwable)
        send(this.errorLiveData, error, threadStrategy)
    }

    protected open fun complete(
        completeEvent: C,
        threadStrategy: ThreadStrategy = DefaultThread
    ) {
        send(contentLiveData, completeEvent, threadStrategy)
    }

    protected open fun <T> send(
        liveData: MutableLiveData<T>,
        data: T?,
        threadStrategy: ThreadStrategy
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