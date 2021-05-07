package com.smlnskgmail.jaman.githubclient.presenter.profileslist

import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListView

interface ProfilesListPresenter {

    fun init(
        gitHubProfilesApi: GitHubProfilesApi,
        profilesListView: ProfilesListView
    )

    fun reloadProfiles()
    fun loadMoreProfiles()

    fun isProfilesIsLoading(): Boolean
    fun isLastPage(): Boolean

    fun profileSelect(gitHubProfile: GitHubShortProfile)

}
