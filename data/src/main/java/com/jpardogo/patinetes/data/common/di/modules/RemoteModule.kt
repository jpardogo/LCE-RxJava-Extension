package com.jpardogo.patinetes.data.common.di.modules

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.jpardogo.patinetes.data.BuildConfig
import com.jpardogo.patinetes.data.common.AccessTokenInterceptor
import com.jpardogo.patinetes.data.common.AccessTokenProvider
import com.jpardogo.patinetes.data.common.AccessTokenProviderImpl
import com.jpardogo.patinetes.data.common.di.annotations.DataScope
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
    fun provideAccessTokenProvider(): AccessTokenProvider = AccessTokenProviderImpl()

    @Provides
    @DataScope
    fun provideAccessTokenInterceptor(accessTokenProvider: AccessTokenProvider) =
        AccessTokenInterceptor(accessTokenProvider)

    @Provides
    @DataScope
    fun provideOkHttpClient(
        cache: Cache,
        logging: HttpLoggingInterceptor,
        okHttpBuilder: OkHttpClient.Builder,
        accessTokenInterceptor: AccessTokenInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(logging)
        .addInterceptor(accessTokenInterceptor)
        .build()

    @Provides
    @DataScope
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient =
        ApolloClient.builder()
            .serverUrl(BuildConfig.BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
}
