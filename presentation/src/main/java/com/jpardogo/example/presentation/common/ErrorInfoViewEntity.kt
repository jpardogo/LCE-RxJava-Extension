package com.jpardogo.example.presentation.common

import timber.log.Timber

class ErrorInfoViewEntity private constructor(throwable: Throwable) {
    companion object {
        //Use this factory to initialise you custom info for the view
        fun newInstance(throwable: Throwable) =
            ErrorInfoViewEntity(throwable)
    }

    init {
        Timber.e(throwable.localizedMessage)
    }
}