package com.smlnskgmail.jaman.githubclient.view.profileslist

import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

interface ProfilesListView {

    fun showProfilesList(
        shortProfiles: List<GitHubShortProfile>
    )

    fun addToProfilesList(
        shortProfiles: List<GitHubShortProfile>
    )

    fun showLoadError()

    fun showProfileInfo(
        gitHubProfile: GitHubShortProfile
    )

}
