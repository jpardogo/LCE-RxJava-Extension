package com.jpardogo.patinetes.domain.common

import io.reactivex.Scheduler

interface ThreadScheduler {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun main(): Scheduler
}