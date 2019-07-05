package com.jpardogo.example.presentation.common.di.modules

import com.jpardogo.example.presentation.feature.ExampleActivityBuilder
import dagger.Module

@Module(
    includes = [ExampleActivityBuilder::class]
)
abstract class ActivitiesModule