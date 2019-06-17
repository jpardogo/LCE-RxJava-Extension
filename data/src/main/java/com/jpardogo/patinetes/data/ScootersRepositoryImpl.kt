package com.jpardogo.patinetes.data

import com.jpardogo.patinetes.domain.ScootersRepository
import io.reactivex.Single

class ScootersRepositoryImpl : ScootersRepository {
    override fun getScooter(): Single<Boolean> = Single.just(true)
}