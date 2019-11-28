package com.jpardogo.example.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.jpardogo.lce.LceErrorViewEntity
import com.jpardogo.lce.LceSingleLiveEvent
import com.nhaarman.mockitokotlin2.mock

class TestObserver<T> : Observer<T> {

    val observedValues = mutableListOf<T?>()

    override fun onChanged(value: T?) {
        observedValues.add(value)
    }
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}

fun <C> getLceObservers(lce: LceSingleLiveEvent<C>): Triple<TestObserver<Boolean>, Observer<C>, Observer<LceErrorViewEntity>> =
    Triple(
        lce.loadingLiveEvent.testObserver(),
        mock<Observer<C>>().apply { lce.contentLiveEvent.observeForever(this) },
        mock<Observer<LceErrorViewEntity>>().apply { lce.errorLiveEvent.observeForever(this) }
    )