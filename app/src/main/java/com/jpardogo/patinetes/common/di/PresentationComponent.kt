package com.jpardogo.patinetes.common.di

import com.jpardogo.patinetes.PatinetesApp
import com.jpardogo.patinetes.common.di.annotations.PresentationScope
import com.jpardogo.patinetes.common.di.modules.ActivitiesModule
import com.jpardogo.patinetes.common.di.modules.PresentationModule
import com.jpardogo.patinetes.data.common.di.DataComponent
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
interface PresentationComponent : AndroidInjector<PatinetesApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PatinetesApp): Builder

        fun dataComponent(dc: DataComponent)
        fun build(): PresentationComponent
    }
}
