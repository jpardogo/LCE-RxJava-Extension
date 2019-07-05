package com.jpardogo.example.presentation.feature

import androidx.lifecycle.ViewModel
import com.jpardogo.example.presentation.common.di.ActivityScope
import com.jpardogo.example.presentation.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ExampleActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun exampleActivity(): ExampleActivity

    @Binds
    @IntoMap
    @ViewModelKey(ExampleViewModel::class)
    abstract fun bindExampleViewModel(viewModel: ExampleViewModel): ViewModel
}