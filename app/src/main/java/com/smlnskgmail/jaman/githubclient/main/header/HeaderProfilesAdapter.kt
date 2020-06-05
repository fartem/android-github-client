package com.smlnskgmail.jaman.githubclient.main.header

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile
import kotlinx.android.synthetic.main.item_profile.view.*

class HeaderProfilesAdapter(
    profiles: List<GitHubShortProfile>,
    private val profileSelectTarget: ProfileSelectTarget
): RecyclerView.Adapter<HeaderProfilesAdapter.ProfileHolder>() {

    companion object {

        private const val optimalImageSize = 150

    }

    private val profiles = mutableListOf<GitHubShortProfile>()

    init {
        this.profiles.addAll(
            profiles
        )
    }

    fun updateProfiles(
        profiles: List<GitHubShortProfile>
    ) {
        this.profiles.clear()
        this.profiles.addAll(
            profiles
        )
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileHolder {
        return ProfileHolder(
            LayoutInflater.from(parent.context!!).inflate(
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

        @SuppressLint("CheckResult")
        fun bind(item: GitHubShortProfile) {
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
