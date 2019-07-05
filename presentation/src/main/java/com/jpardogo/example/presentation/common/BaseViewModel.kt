package com.jpardogo.example.presentation.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(protected val compositeDisposable: CompositeDisposable) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}