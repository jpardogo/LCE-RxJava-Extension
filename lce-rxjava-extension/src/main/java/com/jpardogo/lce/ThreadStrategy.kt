package com.jpardogo.lce

sealed class ThreadStrategy {
    object ForceMainThread : ThreadStrategy()
    object DefaultThread : ThreadStrategy()
}