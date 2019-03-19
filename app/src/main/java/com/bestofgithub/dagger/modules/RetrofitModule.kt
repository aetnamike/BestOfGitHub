package com.bestofgithub.dagger.modules

import com.bestofgithub.constants.RetrofitNames.Companion.GIT_RETROFIT
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class RetrofitModule {

    @Provides
    @Named(GIT_RETROFIT)
    fun provideGitRetrofit() = buildRetrofit("https://api.github.com/")

    fun buildRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}