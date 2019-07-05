package com.jpardogo.example.presentation.feature

import com.jpardogo.example.domain.feature.ExampleDomainEntity

fun ExampleDomainEntity.toViewEntity() = ExampleViewEntity(
    id = id,
    exampleField = exampleField,
    exampleField2 = exampleField2
)