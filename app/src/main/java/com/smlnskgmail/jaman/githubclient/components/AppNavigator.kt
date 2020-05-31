package com.smlnskgmail.jaman.githubclient.components

import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

interface AppNavigator {

    fun showProfileInfoFor(
        gitHubProfile: GitHubShortProfile
    )

}
