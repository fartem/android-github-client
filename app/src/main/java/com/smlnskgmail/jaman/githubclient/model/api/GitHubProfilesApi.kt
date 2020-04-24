package com.smlnskgmail.jaman.githubclient.model.api

interface GitHubProfilesApi {

    fun profilesPortion(
        page: Int,
        profilesLoadCallback: ProfilesLoadCallback
    )

    fun repositoriesFor(
        profile: GitHubProfile,
        repositoriesLoadCallback: RepositoriesLoadCallback
    )

    interface ProfilesLoadCallback {

        fun loadSuccess(
            profiles: List<GitHubProfile>
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
