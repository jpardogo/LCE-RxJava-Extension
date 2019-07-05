package com.jpardogo.example.data.common.di

import android.content.Context
import com.jpardogo.example.data.common.di.annotations.DataScope
import com.jpardogo.example.data.common.di.modules.*
import com.jpardogo.example.domain.feature.GetExampleUseCase
import dagger.BindsInstance
import dagger.Component

@DataScope
@Component(
    modules = [
        DataModule::class,
        RepositoriesModule::class,
        DataSourcesModule::class,
        DataProvidersModule::class,
        RemoteModule::class
    ]
)
interface DataComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Context): Builder

        fun build(): DataComponent
    }

    //UseCases need to be expose to the app which has DataComponent as a dependency

    fun getExampleUseCase(): GetExampleUseCase
}