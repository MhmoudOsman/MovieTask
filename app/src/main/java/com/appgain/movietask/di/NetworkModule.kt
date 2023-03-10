package com.appgain.movietask.di

import android.util.Log
import com.appgain.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TAG = "NetworkModule"
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(20,TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .addInterceptor {
                val original: Request = it.request()
                val originalHttpUrl: HttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "4ade9b5825bc1241c698248fa3d2c2ef")
                    .build()
                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder.build()
                Log.d(TAG, "provideOkHttp: ${request.headers()}")
                it.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideApiService(retrofit:Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}