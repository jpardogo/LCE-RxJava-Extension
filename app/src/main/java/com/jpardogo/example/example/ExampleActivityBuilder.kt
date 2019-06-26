package com.jpardogo.example.example

import androidx.lifecycle.ViewModel
import com.jpardogo.example.common.di.annotations.ActivityScope
import com.jpardogo.example.common.di.annotations.ViewModelKey
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