package com.smlnskgmail.jaman.githubclient.presenter.profileslist

import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListView

interface ProfilesListPresenter {

    fun init(
        gitHubProfilesApi: GitHubProfilesApi,
        profilesListView: ProfilesListView
    )

    fun reloadProfiles()
    fun loadMoreProfiles()

    fun profilesLoading(): Boolean
    fun isLastPage(): Boolean

}
