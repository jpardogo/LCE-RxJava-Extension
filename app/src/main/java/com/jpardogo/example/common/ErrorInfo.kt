package com.jpardogo.example.common

class ErrorInfo private constructor(throwable: Throwable) {
    companion object {
        fun newInstance(throwable: Throwable) = ErrorInfo(throwable)
    }
}