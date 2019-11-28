package com.jpardogo.lce

import androidx.annotation.MainThread

class SingleMutableLiveEvent<T> : SingleLiveEvent<T>(){

    @MainThread
    public override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    public override fun postValue(value: T?) {
        mPending.set(true)
        super.postValue(value)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}