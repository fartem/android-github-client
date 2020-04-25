package com.smlnskgmail.jaman.githubclient.view.profileslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubShortProfile
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfilesListAdapter(
    initShortProfiles: List<GitHubShortProfile>
) : RecyclerView.Adapter<ProfilesListAdapter.ProfileHolder>() {

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

    fun addProfiles(
        shortProfiles: List<GitHubShortProfile>
    ) {
        val lastIndex = itemCount
        this.shortProfiles.addAll(
            shortProfiles
        )
        notifyItemRangeInserted(
            lastIndex,
            shortProfiles.size
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
    ): ProfileHolder {
        return if (viewType == viewTypeLoading) {
            ProgressHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_loading,
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
        holder: ProfileHolder,
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
    ) : RecyclerView.ViewHolder(itemView) {

        open fun bind(gitHubShortProfile: GitHubShortProfile) {
            itemView.profile_login.text = gitHubShortProfile.login
            itemView.profile_type.text = gitHubShortProfile.type
            if (gitHubShortProfile.photoUrl != null) {
                Glide.with(itemView.context!!)
                    .load(gitHubShortProfile.photoUrl)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(itemView.profile_avatar)
            } else {
                itemView.profile_avatar.setImageResource(
                    R.drawable.ic_profile
                )
            }
        }

    }

    inner class ProgressHolder(
        itemView: View
    ) : ProfileHolder(itemView) {

        override fun bind(
            gitHubShortProfile: GitHubShortProfile
        ) {

        }

    }

}
