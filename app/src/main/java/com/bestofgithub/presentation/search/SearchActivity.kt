package com.bestofgithub.presentation.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.bestofgithub.dagger.components.DaggerSearchComponent
import com.bestofgithub.dagger.modules.EndpointModule
import com.bestofgithub.dagger.modules.SearchModule
import com.bestofgithub.domain.models.RepoViewModel
import com.bestofgithub.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import com.bestofgithub.R

class SearchActivity : BaseActivity(), SearchContract.View, RepoListAdapter.RepoClickListener {

    @Inject
    override lateinit var presenter: SearchContract.Presenter

    var inputString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSearchComponent
            .builder()
            .appComponent(appComponent)
            .endpointModule(EndpointModule())
            .searchModule(SearchModule(this))
            .build()
            .inject(this)
        presenter.onViewCreated()
    }

    override fun showSearch() {
        search_button.setOnClickListener {
            count.visibility = GONE
            recycler.visibility = GONE
            inputString = input.text.toString()
            presenter.searchSelected(inputString)
        }
    }

    override fun showResults(repos: List<RepoViewModel>) {
        count.visibility = VISIBLE
        count.text = String.format(getString(R.string.top, repos.size, inputString))
        recycler.visibility = VISIBLE
        recycler.adapter?.let {
            recycler.swapAdapter(RepoListAdapter(repos, this), false)
        } ?: run {
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = RepoListAdapter(repos, this)
        }
    }

    override fun showLoading(loading: Boolean) {
        progress_bar.visibility =
        when (loading) {
            true -> VISIBLE
            false -> GONE
        }
    }

    override fun showError(error: String?) {
        Toast.makeText(this, error?: "Unknown Error", Toast.LENGTH_SHORT).show()
    }

    override fun onRepoClicked(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    override fun getLayout(): Int = R.layout.activity_search
}