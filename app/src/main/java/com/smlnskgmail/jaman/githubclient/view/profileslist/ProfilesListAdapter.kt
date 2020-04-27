package com.smlnskgmail.jaman.githubclient.view.profileslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewAdapter
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewHolder
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewProgressHolder
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubShortProfile
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfilesListAdapter(
    initShortProfiles: List<GitHubShortProfile>
) : ExpandableRecyclerViewAdapter<GitHubShortProfile>() {

    companion object {

        private const val viewTypeItem = 0
        private const val viewTypeLoading = 1

    }

    private val shortProfiles = mutableListOf<GitHubShortProfile>()

    private var loaderIsVisible = false

    init {
        this.shortProfiles.addAll(
            initShortProfiles
        )
    }

    override fun addMore(
        items: List<GitHubShortProfile>
    ) {
        val lastIndex = itemCount
        shortProfiles.addAll(items)
        notifyItemRangeInserted(
            lastIndex,
            items.size
        )
    }

    fun loadingStarted() {
        loaderIsVisible = true
        shortProfiles.add(
            GitHubShortProfile(
                -1,
                "",
                "",
                null
            )
        )
        notifyItemInserted(
            itemCount - 1
        )
    }

    fun loadingEnded() {
        loaderIsVisible = false
        val progressItemIndex = itemCount - 1
        notifyItemRemoved(
            progressItemIndex
        )
        shortProfiles.removeAt(
            progressItemIndex
        )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpandableRecyclerViewHolder<GitHubShortProfile> {
        return if (viewType == viewTypeLoading) {
            ExpandableRecyclerViewProgressHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_load,
                    parent,
                    false
                )
            )
        } else {
            ProfileHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_profile,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(
        holder: ExpandableRecyclerViewHolder<GitHubShortProfile>,
        position: Int
    ) {
        holder.bind(shortProfiles[position])
    }

    override fun getItemViewType(
        position: Int
    ): Int {
        return if (loaderIsVisible) {
            if (position == itemCount - 1) {
                viewTypeLoading
            } else {
                viewTypeItem
            }
        } else {
            viewTypeItem
        }
    }

    override fun getItemCount(): Int {
        return shortProfiles.size
    }

    open inner class ProfileHolder(
        itemView: View
    ) : ExpandableRecyclerViewHolder<GitHubShortProfile>(itemView) {

        @SuppressLint("CheckResult")
        override fun bind(item: GitHubShortProfile) {
            itemView.profile_login.text = item.login
            itemView.profile_type.text = item.type
            if (item.photoUrl != null) {
                val requestOptions = RequestOptions()
                requestOptions.override(150, 150)

                Glide.with(itemView.context!!)
                    .asBitmap()
                    .apply(requestOptions)
                    .load(item.photoUrl)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(itemView.profile_avatar)
            } else {
                itemView.profile_avatar.setImageResource(
                    R.drawable.ic_profile
                )
            }
        }

    }

}
