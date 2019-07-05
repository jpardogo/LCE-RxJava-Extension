package com.jpardogo.example.domain.feature

import com.jpardogo.example.domain.common.SingleUseCase
import com.jpardogo.example.domain.common.ThreadScheduler
import javax.inject.Inject

class GetExampleUseCase @Inject constructor(
    threadScheduler: ThreadScheduler,
    private val featureRepository: FeatureRepository
) : SingleUseCase<GetExampleUseCase.Params, ExampleDomainEntity>(threadScheduler) {
    override fun buildUseCaseSource(params: Params) = featureRepository.getExample(params)

    fun parameters(p1: Double, p2: Double) =
        Params(p1, p2)

    data class Params(val p1: Double, val p2: Double)
}