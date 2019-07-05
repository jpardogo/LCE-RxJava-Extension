package com.jpardogo.example.data.feature.database

import androidx.room.Dao
import androidx.room.Query
import com.jpardogo.example.data.common.database.BaseDao
import io.reactivex.Maybe

@Dao
abstract class ExampleDAO : BaseDao<ExampleRoomEntity> {

    @Query("SELECT * from EXAMPLE WHERE EXAMPLE.id = :queryField")
    abstract fun getInfoExample(queryField: Long): Maybe<ExampleRoomEntity>
}