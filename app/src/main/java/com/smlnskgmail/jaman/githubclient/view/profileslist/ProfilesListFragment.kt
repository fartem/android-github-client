package com.smlnskgmail.jaman.githubclient.view.profileslist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.smlnskgmail.jaman.githubclient.App
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.components.AppLongToast
import com.smlnskgmail.jaman.githubclient.components.AppNavigator
import com.smlnskgmail.jaman.githubclient.components.BaseFragment
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewPagination
import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile
import com.smlnskgmail.jaman.githubclient.presenter.profileslist.ProfilesListPresenter
import com.smlnskgmail.jaman.githubclient.presenter.profileslist.ProfilesListPresenterImpl
import kotlinx.android.synthetic.main.fragment_profiles_list.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ProfilesListFragment : BaseFragment(),
    ProfilesListView,
    KodeinAware,
    ProfilesListAdapter.ProfileSelectTarget {

    override lateinit var kodein: Kodein

    private val gitHubProfilesApi: GitHubProfilesApi by instance<GitHubProfilesApi>()

    private lateinit var profilesListPresenter: ProfilesListPresenter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        kodein = (context?.applicationContext as App).kodein

        profilesListPresenter = ProfilesListPresenterImpl()
        profilesListPresenter.init(
            gitHubProfilesApi,
            this
        )
        profiles_list.messageView = profiles_list_message_view
        profiles_list_refresh.isEnabled = false
    }

    override fun showProfilesList(
        shortProfiles: List<GitHubShortProfile>
    ) {
        val adapter = ProfilesListAdapter(
            shortProfiles,
            this
        )

        val layoutManager = LinearLayoutManager(context)
        profiles_list.layoutManager = layoutManager
        profiles_list.addOnScrollListener(
            object : ExpandableRecyclerViewPagination(layoutManager) {
                override fun loadMoreItems() {
                    adapter.loadingStarted()
                    profilesListPresenter.loadMoreProfiles()
                }

                override fun isLastPage(): Boolean {
                    return profilesListPresenter.isProfilesIsLoading()
                }

                override fun isLoading(): Boolean {
                    return profilesListPresenter.isLastPage()
                }
            }
        )
        profiles_list.adapter = adapter

        hideLoadProgress()
        enableRefresh()
    }

    private fun hideLoadProgress() {
        profiles_list_progress_bar_top.visibility = View.GONE
        profiles_list_progress_bar_center.visibility = View.GONE
    }

    private fun enableRefresh() {
        profiles_list_refresh.isRefreshing = false
        profiles_list_refresh.isEnabled = true
        profiles_list_refresh.setOnRefreshListener {
            profiles_list_progress_bar_top.visibility = View.VISIBLE
            profilesListPresenter.reloadProfiles()
        }
    }

    override fun addToProfilesList(
        shortProfiles: List<GitHubShortProfile>
    ) {
        (profiles_list.adapter as ProfilesListAdapter).loadingEnded()
        (profiles_list.adapter as ProfilesListAdapter).addMore(shortProfiles)
    }

    override fun showLoadError() {
        if (profiles_list.adapter != null) {
            (profiles_list.adapter as ProfilesListAdapter).loadingEnded()
        } else {
            profiles_list.adapter = ProfilesListAdapter(
                emptyList(),
                this
            )
        }
        AppLongToast(
            requireContext(),
            getString(R.string.message_load_error)
        ).show()

        hideLoadProgress()
        enableRefresh()
    }

    override fun showProfileInfo(gitHubProfile: GitHubShortProfile) {
        (activity as AppNavigator).showProfileInfoFor(
            gitHubProfile
        )
    }

    override fun title() = getString(R.string.title_profiles_list)

    override fun enableBackPress() = false

    override fun layoutResId() = R.layout.fragment_profiles_list

    override fun profileSelected(gitHubProfile: GitHubShortProfile) {
        profilesListPresenter.profileSelect(
            gitHubProfile
        )
    }

}
