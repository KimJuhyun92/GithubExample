package com.project.githubexample.di

import com.project.githubexample.BuildConfig
import com.project.githubexample.data.datasource.local.GithubLocalSource
import com.project.githubexample.data.datasource.remote.GithubRemoteSource
import com.project.githubexample.data.datasource.remote.api.GithubApiService
import com.project.githubexample.data.repository.GithubRepository
import com.project.githubexample.network.AuthInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = "https://api.github.com"

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor() : AuthInterceptor {
        return AuthInterceptor()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): GithubApiService = retrofit.create(GithubApiService::class.java)

    @Provides
    @Singleton
    fun provideGithubRemoteDataSource(api: GithubApiService): GithubRemoteSource {
        return GithubRemoteSource(api)
    }

    @Provides
    @Singleton
    fun provideGithubLocalDataSource(): GithubLocalSource {
        return GithubLocalSource()
    }

    @Provides
    @Singleton
    fun provideGithubRepository(api: GithubApiService, remoteDataSource: GithubRemoteSource, localDataSource: GithubLocalSource): GithubRepository {
        return GithubRepository(api, remoteDataSource, localDataSource)
    }

}