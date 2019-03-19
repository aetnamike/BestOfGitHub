package com.bestofgithub.domain.validation

import com.bestofgithub.data.remote.json.Repository
import com.bestofgithub.domain.models.RepoViewModel

class RepoSearchResponseValidator {
    fun validateAndTransform(response: List<Repository>?): List<RepoViewModel> {

        val repos = mutableListOf<RepoViewModel>()
        response?.let {
            it.forEach { repo ->
                //for the sake of validating something, our required fields
                if (!repo.html_url.isNullOrBlank() &&
                    !repo.name.isNullOrBlank() &&
                    repo.stargazers_count != null
                ) {
                    repos.add(
                        RepoViewModel(
                            repo.html_url,
                            repo.name,
                            repo.stargazers_count
                        )
                    )
                }
            }
        }
        if (repos.size >= 3) {
            return repos.sortedBy { it.stars }.asReversed().subList(0,3)
        } else {
            return repos.sortedBy { it.stars }.asReversed()
        }
    }
}