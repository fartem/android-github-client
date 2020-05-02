package com.smlnskgmail.jaman.githubclient.presenter.profileslist

import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCache
import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListView

interface ProfilesListPresenter {

    fun init(
        gitHubProfilesApi: GitHubProfilesApi,
        appCache: AppCache,
        profilesListView: ProfilesListView
    )

    fun reloadProfiles()
    fun loadMoreProfiles()

    fun profilesLoading(): Boolean
    fun isLastPage(): Boolean

    fun profileSelect(
        gitHubProfile: GitHubShortProfile
    )

}
