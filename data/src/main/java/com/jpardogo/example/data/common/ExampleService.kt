package com.jpardogo.example.data.common

import com.jpardogo.example.data.feature.models.ExampleAPIEntity
import io.reactivex.Maybe
import retrofit2.http.*

interface ExampleService {

    @Headers("$HEADER_NO_AUTHORIZATION:true")
    @GET("/example/{pathExample}")
    fun example(@Path("pathExample") pathExample: String, @Query("queryExample") queryExample: String): Maybe<ExampleAPIEntity>

    @GET
    fun example(@Url fullOverrideUrl: String): Maybe<ExampleAPIEntity>
}