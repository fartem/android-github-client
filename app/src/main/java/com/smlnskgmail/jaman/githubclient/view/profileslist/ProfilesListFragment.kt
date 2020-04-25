package com.smlnskgmail.jaman.githubclient.view.profileslist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.smlnskgmail.jaman.githubclient.App
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.components.BaseFragment
import com.smlnskgmail.jaman.githubclient.components.RecyclerViewPagination
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubShortProfile
import com.smlnskgmail.jaman.githubclient.presenter.profileslist.ProfilesListPresenter
import com.smlnskgmail.jaman.githubclient.presenter.profileslist.ProfilesListPresenterImpl
import kotlinx.android.synthetic.main.fragment_profiles_list.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ProfilesListFragment : BaseFragment(), ProfilesListView, KodeinAware {

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

        profiles_list_refresh.isEnabled = false
    }

    override fun showProfilesList(
        shortProfiles: List<GitHubShortProfile>
    ) {
        val adapter = ProfilesListAdapter(shortProfiles)

        val layoutManager = LinearLayoutManager(context)
        profiles_list.layoutManager = layoutManager
        profiles_list.addOnScrollListener(object : RecyclerViewPagination(layoutManager) {
            override fun loadMoreItems() {
                adapter.loadingStarted()
                profilesListPresenter.loadMoreProfiles()
            }

            override fun isLastPage(): Boolean {
                return profilesListPresenter.profilesLoading()
            }

            override fun isLoading(): Boolean {
                return profilesListPresenter.isLastPage()
            }
        })
        profiles_list.adapter = adapter

        // TODO: refactor visibility set
        profiles_list_progress_bar_top.visibility = View.GONE
        profiles_list_progress_bar_center.visibility = View.GONE
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
        (profiles_list.adapter as ProfilesListAdapter).addProfiles(
            shortProfiles
        )
    }

    override fun showLoadLatest() {

    }

    override fun showLoadError() {

    }

    override fun layoutResId(): Int {
        return R.layout.fragment_profiles_list
    }

}
