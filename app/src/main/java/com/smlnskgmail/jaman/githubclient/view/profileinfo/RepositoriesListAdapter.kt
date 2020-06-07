package com.smlnskgmail.jaman.githubclient.view.profileinfo

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonObject
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewAdapter
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewHolder
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewProgressHolder
import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubRepository
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoriesListAdapter(
    initRepositories: List<GitHubRepository>,
    private val languages: JsonObject
) : ExpandableRecyclerViewAdapter<GitHubRepository>() {

    private val repositories = mutableListOf<GitHubRepository>()

    init {
        repositories.addAll(
            initRepositories
        )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpandableRecyclerViewHolder<GitHubRepository> {
        return if (viewType == viewTypeLoading) {
            ExpandableRecyclerViewProgressHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_load,
                    parent,
                    false
                )
            )
        } else {
            RepositoryHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_repository,
                    parent,
                    false
                )
            )
        }
    }

    override fun items(): MutableList<GitHubRepository> {
        return repositories
    }

    override fun loaderItem(): GitHubRepository {
        return GitHubRepository(
            "",
            "",
            "",
            -1,
            -1
        )
    }

    inner class RepositoryHolder(
        itemView: View
    ): ExpandableRecyclerViewHolder<GitHubRepository>(itemView) {

        override fun bind(item: GitHubRepository) {
            itemView.repository_name.text = item.name
            if (item.description.isNotEmpty()) {
                itemView.repository_description.visibility = View.VISIBLE
                itemView.repository_description.text = item.description
            } else {
                itemView.repository_description.visibility = View.GONE
            }
            itemView.repository_stars.text = item.stars.toString()
            itemView.repository_forks.text = item.forks.toString()

            if (item.language.isNotEmpty()) {
                itemView.repository_language.visibility = View.VISIBLE
                itemView.repository_language.text = item.language
                val languageColor = if (languages.has(item.language)) {
                    languages.get(item.language).asString
                } else {
                    "#000000"
                }
                itemView.repository_language.compoundDrawablesRelative.forEach {
                    it?.colorFilter = PorterDuffColorFilter(
                        Color.parseColor(
                            languageColor
                        ),
                        PorterDuff.Mode.SRC_ATOP
                    )
                }
            } else {
                itemView.repository_language.visibility = View.GONE
            }
        }

    }

}
