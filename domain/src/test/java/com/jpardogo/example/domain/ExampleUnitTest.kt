package com.jpardogo.example.domain

import com.jpardogo.example.domain.feature.FeatureRepository
import com.jpardogo.example.domain.feature.GetExampleUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import mockThreadScheduler
import org.junit.Test

class ExampleUnitTest {

    private lateinit var sut: GetExampleUseCase

    @Test
    fun `verify interactions happy case`() {
        // given
        val exampleRepository = mock<FeatureRepository> {
            on { getExample(any()) } doReturn Single.just(mock())
        }

        sut = GetExampleUseCase(
            mockThreadScheduler,
            exampleRepository
        )

        // when
        val testObserver = sut.execute(GetExampleUseCase.Params(1.0, 1.0)).test()

        // then
        testObserver
            .assertSubscribed()
            .assertNoErrors()
            .assertComplete()
        verify(exampleRepository).getExample(any())
    }
}