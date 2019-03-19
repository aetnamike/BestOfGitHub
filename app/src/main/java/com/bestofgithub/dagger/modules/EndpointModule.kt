package com.bestofgithub.dagger.modules

import com.bestofgithub.constants.RetrofitNames.Companion.GIT_RETROFIT
import com.bestofgithub.data.remote.service.GitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class EndpointModule {
    @Provides
    fun provideGitService(@Named(GIT_RETROFIT) retrofit: Retrofit) = retrofit.create(GitService::class.java)
}