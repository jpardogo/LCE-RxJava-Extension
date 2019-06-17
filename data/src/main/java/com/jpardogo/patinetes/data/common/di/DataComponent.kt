package com.jpardogo.patinetes.data.common.di

import android.content.Context
import com.jpardogo.patinetes.data.common.di.annotations.DataScope
import com.jpardogo.patinetes.data.common.di.modules.*
import com.jpardogo.patinetes.domain.GetScootersUseCase
import dagger.BindsInstance
import dagger.Component

@DataScope
@Component(
    modules = [
        DataModule::class,
        RepositoriesModule::class,
        DataSourcesModule::class,
        DataStoresModule::class,
        NetModule::class
    ]
)
interface DataComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Context): Builder

        fun build(): DataComponent
    }

    //UseCases need to be expose to presentation layer

    fun getScootersUseCase(): GetScootersUseCase
}