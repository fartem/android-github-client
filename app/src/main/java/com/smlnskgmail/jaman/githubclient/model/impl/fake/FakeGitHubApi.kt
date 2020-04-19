package com.smlnskgmail.jaman.githubclient.model.impl.fake

import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfile
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.GitHubRepository

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
