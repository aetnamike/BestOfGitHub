package com.bestofgithub.dagger.modules

import com.bestofgithub.data.remote.service.GitService
import com.bestofgithub.domain.usecases.GetGitRepoUseCase
import com.bestofgithub.domain.validation.RepoSearchResponseValidator
import com.bestofgithub.presentation.search.SearchContract
import com.bestofgithub.presentation.search.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
class SearchModule (private val view: SearchContract.View) {

    @Provides
    internal fun provideView(): SearchContract.View = view

    @Provides
    fun provideRepoSearchResponseValidator(): RepoSearchResponseValidator = RepoSearchResponseValidator()

    @Provides
    fun provideUseCase(
        service: GitService,
        validator: RepoSearchResponseValidator
    ): GetGitRepoUseCase = GetGitRepoUseCase(service, validator)

    @Provides
    fun providePresenter(service: GitService, validator: RepoSearchResponseValidator): SearchContract.Presenter =
        SearchPresenter(view, service, validator)
}