package com.jpardogo.example.data.common.di.modules

import android.content.Context
import com.jpardogo.example.data.BuildConfig
import com.jpardogo.example.data.common.di.annotations.DataScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class RemoteModule {

    companion object {
        private const val CACHE_SIZE: Long = 10 * 1024 * 1024
    }

    @Provides
    @DataScope
    fun provideCache(context: Context) = Cache(context.cacheDir, CACHE_SIZE)

    @Provides
    @DataScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @DataScope
    fun provideOkHttpClient(
        cache: Cache,
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(logging)
        .build()
}
