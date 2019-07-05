package com.jpardogo.example.data.feature.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "EXAMPLE"
)
data class ExampleRoomEntity(
    @PrimaryKey val id: Long,
    val exampleField: String,
    val exampleField2: String?
)