package com.jpardogo.example.data.common.rx

import com.jpardogo.example.domain.common.ThreadScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxScheduler : ThreadScheduler {
    override fun computation() = Schedulers.computation()
    override fun io() = Schedulers.io()
    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}