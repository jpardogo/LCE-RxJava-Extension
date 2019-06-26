package com.jpardogo.example.domain.common

import io.reactivex.Scheduler

interface ThreadScheduler {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun main(): Scheduler
}