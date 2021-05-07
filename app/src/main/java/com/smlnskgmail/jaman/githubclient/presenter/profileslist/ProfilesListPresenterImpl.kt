package com.smlnskgmail.jaman.githubclient.presenter.profileslist

import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListView

class ProfilesListPresenterImpl : ProfilesListPresenter {

    private lateinit var gitHubProfilesApi: GitHubProfilesApi
    private lateinit var profilesListView: ProfilesListView

    private var page: Int = 0

    private var isProfilesIsLoading = false
    private var isLastPage = false

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
        isLastPage = false
        loadMoreProfiles()
    }

    override fun loadMoreProfiles() {
        isProfilesIsLoading = true
        gitHubProfilesApi.profilesPortion(
            ++page,
            object : GitHubProfilesApi.ProfilesLoadCallback {
                override fun loadSuccess(shortProfiles: List<GitHubShortProfile>) {
                    if (page == 1) {
                        profilesListView.showProfilesList(shortProfiles)
                    } else {
                        profilesListView.addToProfilesList(shortProfiles)
                    }
                    isProfilesIsLoading = false
                }

                override fun loadError() {
                    isLastPage = true
                    isProfilesIsLoading = false

                    profilesListView.showLoadError()
                    isProfilesIsLoading = false
                }
            }
        )
    }

    override fun isProfilesIsLoading() = isProfilesIsLoading

    override fun isLastPage() = isLastPage

    override fun profileSelect(gitHubProfile: GitHubShortProfile) {
        profilesListView.showProfileInfo(gitHubProfile)
    }

}
