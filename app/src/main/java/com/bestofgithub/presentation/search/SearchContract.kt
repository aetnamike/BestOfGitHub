package com.bestofgithub.presentation.search

import com.bestofgithub.domain.models.RepoViewModel
import com.bestofgithub.presentation.BasePresenter
import com.bestofgithub.presentation.BaseView

interface SearchContract {

    interface View : BaseView<Presenter> {
        fun showSearch()
        fun showResults(repos: List<RepoViewModel>)
    }

    interface Presenter : BasePresenter {
        fun onPause()
        fun searchSelected(org: String)
    }
}