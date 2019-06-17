package com.jpardogo.patinetes.map

import androidx.lifecycle.ViewModel
import com.jpardogo.patinetes.common.di.annotations.ActivityScope
import com.jpardogo.patinetes.common.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MapActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mapActivity(): MapActivity

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(viewModel: MapViewModel): ViewModel
}