package com.jpardogo.patinetes.domain

import com.jpardogo.patinetes.domain.common.SingleUseCaseNoParams
import com.jpardogo.patinetes.domain.common.ThreadScheduler
import io.reactivex.Single
import javax.inject.Inject

class GetScootersUseCase @Inject constructor(
    threadScheduler: ThreadScheduler,
    private val scootersRepository: ScootersRepository
) : SingleUseCaseNoParams<Boolean>(threadScheduler) {
    override fun buildUseCaseSource(): Single<Boolean> {
        return scootersRepository.getScooter()
    }
}