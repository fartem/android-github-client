package com.smlnskgmail.jaman.githubclient.view.profileslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubShortProfile
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfilesListAdapter(
    private val shortProfiles: List<GitHubShortProfile>
) : RecyclerView.Adapter<ProfilesListAdapter.ProfileHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileHolder {
        return ProfileHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_profile,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ProfileHolder,
        position: Int
    ) {
        holder.bind(shortProfiles[position])
    }

    override fun getItemCount(): Int {
        return shortProfiles.size
    }

    inner class ProfileHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(gitHubShortProfile: GitHubShortProfile) {
            itemView.profile_login.text = gitHubShortProfile.login
            itemView.profile_type.text = gitHubShortProfile.type
            if (gitHubShortProfile.photoUrl != null) {
                Glide.with(itemView.context!!)
                    .load(gitHubShortProfile.photoUrl)
                    .into(itemView.profile_avatar)
            }
        }

    }

}
