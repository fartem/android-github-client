package com.smlnskgmail.jaman.githubclient.model.api.cache

import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

interface AppCache {

    companion object {

        const val profilesMaxCount = 5

    }

    fun saveShowedUser(
        gitHubShortProfile: GitHubShortProfile
    )

    fun showedUsers(): List<GitHubShortProfile>

    fun addShowedUsersUpdateTarget(
        appCacheParameterTarget: AppCacheParameterTarget
    )
    fun removeShowedUsersUpdateTarget(
        appCacheParameterTarget: AppCacheParameterTarget
    )

}
