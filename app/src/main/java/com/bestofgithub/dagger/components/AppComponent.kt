package com.bestofgithub.dagger.components

import com.bestofgithub.constants.RetrofitNames.Companion.GIT_RETROFIT
import com.bestofgithub.dagger.modules.*
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named

@Component(modules = [EndpointModule::class, RetrofitModule::class])
interface AppComponent {

    @Named(GIT_RETROFIT)
    fun getGitRetrofit() : Retrofit
}