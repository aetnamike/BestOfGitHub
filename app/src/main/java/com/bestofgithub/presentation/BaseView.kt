package com.bestofgithub.presentation

interface BaseView<P: BasePresenter> {
    var presenter: P
    fun showLoading(loading: Boolean)
    fun showError(error: String?)
}
