package com.jpardogo.patinetes.common.di.modules

import androidx.lifecycle.ViewModel
import com.jpardogo.patinetes.common.di.annotations.ViewModelKey
import com.jpardogo.patinetes.map.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(viewModel: MapViewModel): ViewModel
}