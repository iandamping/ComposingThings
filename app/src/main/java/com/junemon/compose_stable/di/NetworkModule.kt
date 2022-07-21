package com.junemon.compose_stable.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.junemon.compose_stable.core.datasource.network.MovieApi
import com.junemon.compose_stable.core.datasource.network.NetworkConstant
import com.junemon.compose_stable.core.datasource.network.NetworkConstant.cacheSize
import com.junemon.compose_stable.core.datasource.network.NewsApi
import com.junemon.compose_stable.core.datasource.network.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideOkHttpClientCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, cacheSize)

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor { chain ->
            with(chain) {
                proceed(this.request().newBuilder().build())
            }
        }.build()

    @Provides
    @Singleton
    @PokemonApiInterface
    fun providePokemonApiRetrofit(okHttpClient: OkHttpClient): PokemonApi = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl(NetworkConstant.POKEMON_BASE_URL)
        .build()
        .create(PokemonApi::class.java)

    @Provides
    @Singleton
    @MovieApiInterface
    fun provideMovieApiRetrofit(okHttpClient: OkHttpClient): MovieApi = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl(NetworkConstant.MOVIE_BASE_URL)
        .build()
        .create(MovieApi::class.java)

    @Provides
    @Singleton
    @NewsApiInterface
    fun provideNewsApiRetrofit(okHttpClient: OkHttpClient): NewsApi = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl(NetworkConstant.NEWS_BASE_URL)
        .build()
        .create(NewsApi::class.java)
}