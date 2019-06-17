package com.jpardogo.patinetes.common.di.modules

import com.jpardogo.patinetes.map.MapActivityBuilder
import dagger.Module

@Module(
    includes = [MapActivityBuilder::class]
)
abstract class ActivitiesModule