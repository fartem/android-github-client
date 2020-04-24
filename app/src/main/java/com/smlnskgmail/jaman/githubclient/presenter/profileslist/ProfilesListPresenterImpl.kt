package com.smlnskgmail.jaman.githubclient.presenter.profileslist

import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfile
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListView

class ProfilesListPresenterImpl : ProfilesListPresenter {

    private lateinit var gitHubProfilesApi: GitHubProfilesApi
    private lateinit var profilesListView: ProfilesListView

    private var page: Int = 0

    override fun init(
        gitHubProfilesApi: GitHubProfilesApi,
        profilesListView: ProfilesListView
    ) {
        this.gitHubProfilesApi = gitHubProfilesApi
        this.profilesListView = profilesListView
        loadMoreProfiles()
    }

    override fun reloadProfiles() {
        page = 0
        loadMoreProfiles()
    }

    override fun loadMoreProfiles() {
        gitHubProfilesApi.profilesPortion(
            page++,
            object : GitHubProfilesApi.ProfilesLoadCallback {
                override fun loadSuccess(
                    profiles: List<GitHubProfile>
                ) {
                    profilesListView.showProfilesList(
                        profiles
                    )
                }

                override fun loadError() {
                    profilesListView.showLoadError()
                }
            }
        )
    }

}
