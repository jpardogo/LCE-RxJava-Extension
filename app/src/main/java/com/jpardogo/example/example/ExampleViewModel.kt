package com.jpardogo.example.example

import com.jpardogo.example.common.BaseViewModel
import com.jpardogo.example.common.ErrorInfo
import com.jpardogo.example.domain.GetExampleUseCase
import com.jpardogo.lce.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    private val getExampleUseCase: GetExampleUseCase,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    private val exampleLceMutableLiveData = MutableLceLiveData<Boolean>()
    val exampleLceLiveData = exampleLceMutableLiveData.asImmutableLiveData()

    fun doSomething(p1: Double, p2: Double) {
        getExampleUseCase
            .execute(getExampleUseCase.parameters(p1, p2))
            .toLce()
            .subscribeLce({ content ->
                exampleLceMutableLiveData.content(content)
            }, { throwable ->
                val lceError = LceErrorViewEntity.newInstance(
                    ErrorInfo.newInstance(throwable),
                    stringMessage = "Error fetching scooters"
                )
                exampleLceMutableLiveData.error(lceError)
            }, { isLoading ->
                exampleLceMutableLiveData.isLoading(isLoading)
            }).addTo(compositeDisposable)
    }
}