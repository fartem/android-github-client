package com.smlnskgmail.jaman.githubclient.view.profileinfo

import com.smlnskgmail.jaman.githubclient.model.api.GitHubRepository
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubExpandedProfile

interface ProfileInfoView {

    fun showProfile(
        expandedProfile: GitHubExpandedProfile
    )

    fun showRepositories(
        gitHubRepositories: List<GitHubRepository>
    )
    fun addToRepositoriesList(
        gitHubRepositories: List<GitHubRepository>
    )

    fun showLoadError()

}
