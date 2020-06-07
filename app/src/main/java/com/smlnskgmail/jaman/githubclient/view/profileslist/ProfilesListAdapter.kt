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
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfilesListAdapter(
    initShortProfiles: List<GitHubShortProfile>,
    private val profileSelectTarget: ProfileSelectTarget
) : ExpandableRecyclerViewAdapter<GitHubShortProfile>() {

    companion object {

        private const val optimalImageSize = 150

    }

    private val profiles = mutableListOf<GitHubShortProfile>()

    init {
        this.profiles.addAll(
            initShortProfiles
        )
    }

    fun updateProfiles(
        profiles: List<GitHubShortProfile>
    ) {
        items().clear()
        items().addAll(profiles)
        notifyDataSetChanged()
    }

    override fun items(): MutableList<GitHubShortProfile> {
        return profiles
    }

    override fun loaderItem(): GitHubShortProfile {
        return GitHubShortProfile(
            -1,
            "",
            "",
            null
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
            ShortProfileHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_profile,
                    parent,
                    false
                ),
                profileSelectTarget
            )
        }
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    inner class ShortProfileHolder(
        itemView: View,
        private val profileSelectTarget: ProfileSelectTarget
    ) : ExpandableRecyclerViewHolder<GitHubShortProfile>(itemView) {

        @SuppressLint("CheckResult")
        override fun bind(item: GitHubShortProfile) {
            itemView.profile_login.text = item.login
            itemView.profile_type.text = item.type
            if (item.photoUrl != null) {
                val requestOptions = RequestOptions()
                requestOptions.override(
                    optimalImageSize,
                    optimalImageSize
                )

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
            itemView.setOnClickListener {
                profileSelectTarget.profileSelected(
                    item
                )
            }
        }

    }

    interface ProfileSelectTarget {

        fun profileSelected(
            gitHubProfile: GitHubShortProfile
        )

    }

}
