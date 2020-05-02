package com.smlnskgmail.jaman.githubclient.presenter.profileslist

import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCache
import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListView

class ProfilesListPresenterImpl : ProfilesListPresenter {

    private lateinit var gitHubProfilesApi: GitHubProfilesApi
    private lateinit var appCache: AppCache
    private lateinit var profilesListView: ProfilesListView

    private var page: Int = 0

    private var profilesLoading = false
    private var isLastPage = false

    override fun init(
        gitHubProfilesApi: GitHubProfilesApi,
        appCache: AppCache,
        profilesListView: ProfilesListView
    ) {
        this.gitHubProfilesApi = gitHubProfilesApi
        this.appCache = appCache
        this.profilesListView = profilesListView
        loadMoreProfiles()
    }

    override fun reloadProfiles() {
        page = 0
        isLastPage = false
        loadMoreProfiles()
    }

    override fun loadMoreProfiles() {
        profilesLoading = true
        gitHubProfilesApi.profilesPortion(
            ++page,
            object : GitHubProfilesApi.ProfilesLoadCallback {
                override fun loadSuccess(
                    shortProfiles: List<GitHubShortProfile>
                ) {
                    if (page == 1) {
                        profilesListView.showProfilesList(
                            shortProfiles
                        )
                    } else {
                        profilesListView.addToProfilesList(
                            shortProfiles
                        )
                    }
                    profilesLoading = false
                }

                override fun loadError() {
                    isLastPage = true
                    profilesLoading = false

                    profilesListView.showLoadError()
                    profilesLoading = false
                }
            }
        )
    }

    override fun profilesLoading(): Boolean {
        return profilesLoading
    }

    override fun isLastPage(): Boolean {
        return isLastPage
    }

    override fun profileSelect(
        gitHubProfile: GitHubShortProfile
    ) {
        appCache.saveShowedUserId(
            gitHubProfile.login
        )
        profilesListView.showProfileInfo(
            gitHubProfile
        )
    }

}
