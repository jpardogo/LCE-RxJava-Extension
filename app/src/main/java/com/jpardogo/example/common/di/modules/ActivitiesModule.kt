package com.jpardogo.example.common.di.modules

import com.jpardogo.example.example.ExampleActivityBuilder
import dagger.Module

@Module(
    includes = [ExampleActivityBuilder::class]
)
abstract class ActivitiesModule