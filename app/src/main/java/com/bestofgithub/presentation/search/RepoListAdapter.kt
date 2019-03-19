package com.bestofgithub.presentation.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bestofgithub.R
import com.bestofgithub.domain.models.RepoViewModel
import kotlinx.android.synthetic.main.layout_repo_item.view.*

class RepoListAdapter(
    private val repos: List<RepoViewModel>,
    private val repoClickListener: RepoClickListener
) : RecyclerView.Adapter<RepoListAdapter.ItemHolder>() {

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(repos[position], repoClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder = ItemHolder(parent)

    class ItemHolder(parent: ViewGroup):
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_repo_item,
                parent,
                false))
    {
        fun bind(repo: RepoViewModel, repoClickListener: RepoClickListener) {
            with (itemView) {
                card.setOnClickListener {
                    repoClickListener.onRepoClicked(repo.url)
                }
                name.text = repo.name
                stars.text = String.format(resources.getString(R.string.stars, repo.stars))
            }
        }

    }

    interface RepoClickListener {
        fun onRepoClicked(url: String)
    }
}