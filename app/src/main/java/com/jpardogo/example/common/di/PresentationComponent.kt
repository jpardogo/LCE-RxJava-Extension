package com.jpardogo.example.common.di

import com.jpardogo.example.ExampleApp
import com.jpardogo.example.common.di.annotations.PresentationScope
import com.jpardogo.example.common.di.modules.ActivitiesModule
import com.jpardogo.example.common.di.modules.PresentationModule
import com.jpardogo.example.data.common.di.DataComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PresentationScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        PresentationModule::class,
        ActivitiesModule::class
    ],
    dependencies = [
        DataComponent::class
    ]
)
interface PresentationComponent : AndroidInjector<ExampleApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ExampleApp): Builder

        fun dataComponent(dc: DataComponent)
        fun build(): PresentationComponent
    }
}
