package com.jpardogo.patinetes.domain

import io.reactivex.Single

interface ScootersRepository {
    fun getScooter(): Single<Boolean>
}