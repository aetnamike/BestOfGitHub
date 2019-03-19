package com.bestofgithub.domain.usecases

import com.bestofgithub.data.remote.service.GitService
import com.bestofgithub.domain.models.RepoViewModel
import com.bestofgithub.domain.validation.RepoSearchResponseValidator
import javax.inject.Inject

class GetGitRepoUseCase @Inject constructor(
    private val service: GitService,
    private val validator: RepoSearchResponseValidator
): BaseUseCase<GetGitRepoUseCase.Params, RepoViewModel?>() {

    data class Params(val org: String)

    override fun execute(params: Params): RepoViewModel? {
        //TODO this is where we make our call to the service using coroutine
        return null
    }

}