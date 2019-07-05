package com.jpardogo.example.data.common.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.jpardogo.example.data.BuildConfig
import com.jpardogo.example.data.common.ExampleService
import com.jpardogo.example.data.common.database.ExampleRoomDatabase
import com.jpardogo.example.data.common.di.annotations.DataScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataProvidersModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE)

    @Provides
    fun provideExampleService(retrofit: Retrofit): ExampleService =
        retrofit.create(ExampleService::class.java)

    @DataScope
    @Provides
    fun provideExampleDatabase(context: Context) = Room
        .databaseBuilder(
            context,
            ExampleRoomDatabase::class.java,
            BuildConfig.EXAMPLE_DB_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
}