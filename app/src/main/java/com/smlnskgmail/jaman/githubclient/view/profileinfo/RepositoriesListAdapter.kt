package com.smlnskgmail.jaman.githubclient.view.profileinfo

import android.view.ViewGroup
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewAdapter
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewHolder
import com.smlnskgmail.jaman.githubclient.model.api.GitHubRepository

class RepositoriesListAdapter(
    private val repositories: List<GitHubRepository>
) : ExpandableRecyclerViewAdapter<GitHubRepository>() {

    override fun addMore(
        items: List<GitHubRepository>
    ) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpandableRecyclerViewHolder<GitHubRepository> {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: ExpandableRecyclerViewHolder<GitHubRepository>,
        position: Int
    ) {
        TODO("Not yet implemented")
    }



}
