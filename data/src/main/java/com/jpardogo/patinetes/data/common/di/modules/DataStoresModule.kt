package com.jpardogo.patinetes.data.common.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.jpardogo.patinetes.data.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class DataStoresModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE)

}