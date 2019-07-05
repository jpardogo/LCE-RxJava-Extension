package com.jpardogo.example.presentation.feature

import android.os.Bundle
import androidx.activity.viewModels
import com.jpardogo.example.presentation.R
import com.jpardogo.example.presentation.common.BaseDaggerActivity


class ExampleActivity : BaseDaggerActivity() {

    private val viewModel by viewModels<ExampleViewModel> { vmf }


    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        viewModel.exampleLceLiveData.observeLce(this,
            onContent = { content ->

            },
            onError = { error ->

            },
            onLoading = { isLoading ->

            })
        viewModel.doSomething(1.0, 1.0)
    }
}
