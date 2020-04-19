package com.smlnskgmail.jaman.githubclient.model.api

interface GitHubProfilesApi {

    fun profilesPortion(
        offset: Int
    ): List<GitHubProfile>

    fun repositoriesFor(
        profile: GitHubProfile
    ): List<GitHubRepository>

}
