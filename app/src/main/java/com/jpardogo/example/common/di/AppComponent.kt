package com.jpardogo.example.common.di

import com.jpardogo.example.ExampleApp
import com.jpardogo.example.common.di.modules.AppModule
import com.jpardogo.example.data.common.di.DataComponent
import com.jpardogo.example.presentation.common.di.modules.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        PresentationModule::class
    ],
    dependencies = [
        DataComponent::class
    ]
)
interface AppComponent : AndroidInjector<ExampleApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ExampleApp): Builder

        fun dataComponent(dc: DataComponent)
        fun build(): AppComponent
    }
}
