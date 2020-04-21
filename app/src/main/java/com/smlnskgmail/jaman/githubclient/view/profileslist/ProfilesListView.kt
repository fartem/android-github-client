package com.smlnskgmail.jaman.githubclient.view.profileslist

import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfile

interface ProfilesListView {

    fun showProfilesList(
        profiles: List<GitHubProfile>
    )
    fun addToProfilesList(
        profiles: List<GitHubProfile>
    )

}
