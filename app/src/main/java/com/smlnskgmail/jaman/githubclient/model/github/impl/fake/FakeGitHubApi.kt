package com.smlnskgmail.jaman.githubclient.model.github.impl.fake

import com.smlnskgmail.jaman.githubclient.model.github.api.GitHubProfile
import com.smlnskgmail.jaman.githubclient.model.github.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.github.api.GitHubRepository

class FakeGitHubApi : GitHubProfilesApi {

    override fun profilesPortion(
        offset: Int
    ): List<GitHubProfile> {
        TODO("Not yet implemented")
    }

    override fun repositoriesFor(
        profile: GitHubProfile
    ): List<GitHubRepository> {
        TODO("Not yet implemented")
    }

}
