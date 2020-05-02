package com.smlnskgmail.jaman.githubclient.presenter.profileinfo

import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.view.profileinfo.ProfileInfoView

interface ProfileInfoPresenter {

    fun init(
        gitHubProfilesApi: GitHubProfilesApi,
        profileInfoView: ProfileInfoView,
        profileId: String
    )

    fun loadMoreRepositories()

    fun repositoriesLoading(): Boolean
    fun isLastPage(): Boolean

}
