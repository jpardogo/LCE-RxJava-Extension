package com.jpardogo.example.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jpardogo.example.domain.feature.ExampleDomainEntity
import com.jpardogo.example.domain.feature.GetExampleUseCase
import com.jpardogo.example.presentation.feature.ExampleViewModel
import com.jpardogo.lce.CrashReporter
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ExampleUnitTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var sut: ExampleViewModel
    private val crashReporter: CrashReporter = mock()
    private val compositeDisposable: CompositeDisposable = mock()
    private val exmapleDomainModel: ExampleDomainEntity = mock()
    @Test
    fun `verify content liveData is notify in happy case`() {
        // given
        val getExampleUseCase: GetExampleUseCase = mock {
            on { execute(any()) } doReturn Single.just(exmapleDomainModel)
        }

        sut = ExampleViewModel(
            getExampleUseCase,
            crashReporter,
            compositeDisposable
        )

        val (loadingObserver, contentObserver, errorObserver) = getLceObservers(
            sut.exampleLceLiveData
        )
        // when
        sut.doSomething(1.0, 1.0)
        // then
        assertEquals(loadingObserver.observedValues, arrayListOf(true, false))
        verify(contentObserver).onChanged(any())
        verify(errorObserver, never()).onChanged(any())
        verify(compositeDisposable).add(any())
    }
}
