package com.jpardogo.lce

interface CrashReporter {
    fun report(throwable: Throwable?)
}