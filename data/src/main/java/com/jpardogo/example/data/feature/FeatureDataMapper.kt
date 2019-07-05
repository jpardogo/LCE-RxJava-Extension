package com.jpardogo.example.data.feature

import com.jpardogo.example.data.feature.database.ExampleRoomEntity
import com.jpardogo.example.data.feature.models.ExampleAPIEntity
import com.jpardogo.example.domain.feature.ExampleDomainEntity

fun ExampleDomainEntity.toRoom() = ExampleRoomEntity(
    id = id,
    exampleField = exampleField,
    exampleField2 = exampleField2
)

fun ExampleRoomEntity.toDomain() = ExampleDomainEntity(
    id = id,
    exampleField = exampleField,
    exampleField2 = exampleField2
)

fun ExampleAPIEntity.toDomain() = ExampleDomainEntity(
    id = id,
    exampleField = exampleField,
    exampleField2 = exampleField2
)