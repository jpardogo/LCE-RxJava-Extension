package com.jpardogo.example.domain

import com.jpardogo.example.domain.common.SingleUseCase
import com.jpardogo.example.domain.common.ThreadScheduler
import javax.inject.Inject

class GetExampleUseCase @Inject constructor(
    threadScheduler: ThreadScheduler,
    private val exampleRepository: ExampleRepository
) : SingleUseCase<GetExampleUseCase.Params, Boolean>(threadScheduler) {
    override fun buildUseCaseSource(params: Params) = exampleRepository.getExample(params)

    fun parameters(p1: Double, p2: Double) = Params(p1, p2)

    data class Params(val p1: Double, val p2: Double)
}