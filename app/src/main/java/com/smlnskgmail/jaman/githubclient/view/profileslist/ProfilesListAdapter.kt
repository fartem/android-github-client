package com.smlnskgmail.jaman.githubclient.view.profileslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfile
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfilesListAdapter(
    private val profiles: List<GitHubProfile>
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
        holder.bind(profiles[position])
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    inner class ProfileHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(gitHubProfile: GitHubProfile) {
            itemView.profile_login.text = gitHubProfile.login
            itemView.profile_id.text = gitHubProfile.id
            if (gitHubProfile.photoUrl != null) {
                Glide.with(itemView.context!!)
                    .load(gitHubProfile.photoUrl)
                    .into(itemView.profile_avatar)
            }
        }

    }

}
