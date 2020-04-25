package com.smlnskgmail.jaman.githubclient.model.api

import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubExpandedProfile
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubShortProfile

interface GitHubProfilesApi {

    fun profilesPortion(
        page: Int,
        profilesLoadCallback: ProfilesLoadCallback
    )

    fun expandedProfileFor(
        login: String,
        expandedProfileLoadCallback: ProfileLoadCallback
    )

    fun repositoriesFor(
        login: String,
        page: Int,
        repositoriesLoadCallback: RepositoriesLoadCallback
    )

    interface ProfilesLoadCallback {

        fun loadSuccess(
            shortProfiles: List<GitHubShortProfile>
        )
        fun loadError()

    }

    interface ProfileLoadCallback {

        fun loadSuccess(
            expandedProfile: GitHubExpandedProfile
        )
        fun loadError()

    }

    interface RepositoriesLoadCallback {

        fun loadSuccess(
            repositories: List<GitHubRepository>
        )
        fun loadError()

    }

}
