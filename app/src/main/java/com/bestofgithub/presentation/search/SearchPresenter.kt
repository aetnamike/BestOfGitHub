package com.bestofgithub.presentation.search

import com.bestofgithub.data.remote.service.GitService
import com.bestofgithub.domain.models.RepoViewModel
import com.bestofgithub.domain.validation.RepoSearchResponseValidator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val view: SearchContract.View,
    private val service: GitService,
    private val validator: RepoSearchResponseValidator): SearchContract.Presenter {

    var responseDisposable: Disposable? = null

    override fun onViewCreated() {
        view.showSearch()
    }

    override fun onPause() {
        responseDisposable?.dispose()
    }

    override fun searchSelected(org: String) {
        view.showLoading(true)
        responseDisposable = service.getOrgRepos(org)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    view.showLoading(false)
                    showResults(validator.validateAndTransform(result.toList())) },
                { error ->
                    view.showLoading(false)
                    view.showError(error.message) }
            )
    }

    fun showResults(repos: List<RepoViewModel>) {
        view.showResults(repos)
    }




}