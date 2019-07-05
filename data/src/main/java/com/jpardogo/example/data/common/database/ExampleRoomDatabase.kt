package com.jpardogo.example.data.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jpardogo.example.data.feature.database.ExampleDAO
import com.jpardogo.example.data.feature.database.ExampleRoomEntity

@Database(
    entities = [
        ExampleRoomEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class ExampleRoomDatabase : RoomDatabase() {
    abstract fun exampleDAO(): ExampleDAO
}