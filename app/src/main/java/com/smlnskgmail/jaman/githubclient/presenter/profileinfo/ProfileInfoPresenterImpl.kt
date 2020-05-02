package com.smlnskgmail.jaman.githubclient.presenter.profileinfo

import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubRepository
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubExpandedProfile
import com.smlnskgmail.jaman.githubclient.view.profileinfo.ProfileInfoView

class ProfileInfoPresenterImpl : ProfileInfoPresenter {

    private lateinit var gitHubProfilesApi: GitHubProfilesApi
    private lateinit var profileInfoView: ProfileInfoView

    private lateinit var profileId: String

    private var repositoriesPage: Int = 0

    private var repositoriesLoading = false
    private var isLastPage = false

    override fun init(
        gitHubProfilesApi: GitHubProfilesApi,
        profileInfoView: ProfileInfoView,
        profileId: String
    ) {
        this.gitHubProfilesApi = gitHubProfilesApi
        this.profileInfoView = profileInfoView
        this.profileId = profileId

        this.gitHubProfilesApi.expandedProfileFor(
            profileId,
            object : GitHubProfilesApi.ProfileLoadCallback {
                override fun loadSuccess(
                    expandedProfile: GitHubExpandedProfile
                ) {
                    profileInfoView.showProfile(
                        expandedProfile
                    )
                    loadMoreRepositories()
                }

                override fun loadError() {
                    profileInfoView.showLoadError()
                }
            }
        )
    }

    override fun loadMoreRepositories() {
        gitHubProfilesApi.repositoriesFor(
            profileId,
            ++repositoriesPage,
            object : GitHubProfilesApi.RepositoriesLoadCallback {
                override fun loadSuccess(
                    repositories: List<GitHubRepository>
                ) {
                    if (repositoriesPage == 1) {
                        profileInfoView.showRepositories(
                            repositories
                        )
                    } else {
                        profileInfoView.addToRepositoriesList(
                            repositories
                        )
                    }
                }

                override fun loadError() {
                    profileInfoView.showLoadError()
                }
            }
        )
    }

    override fun repositoriesLoading(): Boolean {
        return repositoriesLoading
    }

    override fun isLastPage(): Boolean {
        return isLastPage
    }

}
