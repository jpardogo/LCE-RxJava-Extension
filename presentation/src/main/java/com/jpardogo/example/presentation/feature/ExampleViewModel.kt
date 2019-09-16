package com.jpardogo.example.presentation.feature

import com.jpardogo.example.domain.feature.GetExampleUseCase
import com.jpardogo.example.presentation.common.BaseViewModel
import com.jpardogo.example.presentation.common.ErrorInfoViewEntity
import com.jpardogo.lce.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val getExampleUseCase: GetExampleUseCase,
    crashReporter: CrashReporter,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    private val exampleLceMutableLiveData = MutableLceLiveData<ExampleViewEntity>(crashReporter)
    val exampleLceLiveData = exampleLceMutableLiveData.asImmutable()

    fun doSomething(p1: Double, p2: Double) {
        getExampleUseCase
            .execute(getExampleUseCase.parameters(p1, p2))
            .map { it.toViewEntity() }
            .toLce()
            .subscribeLce({ content ->
                exampleLceMutableLiveData.content(content)
            }, { throwable ->
                exampleLceMutableLiveData.error(
                    LceErrorViewEntity.newInstance(
                        ErrorInfoViewEntity.newInstance(throwable),
                        stringMessage = "Error fetching scooters"
                    )
                )
            }, { isLoading ->
                exampleLceMutableLiveData.isLoading(isLoading)
            }).addTo(compositeDisposable)
    }
}