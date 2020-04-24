package com.smlnskgmail.jaman.githubclient.view.profileslist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.smlnskgmail.jaman.githubclient.App
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.components.BaseFragment
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfile
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
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

    private val profiles = mutableListOf<GitHubProfile>()

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
    }

    override fun showProfilesList(
        profiles: List<GitHubProfile>
    ) {
        this.profiles.addAll(profiles)
        profiles_list.layoutManager = LinearLayoutManager(context)
        profiles_list.adapter = ProfilesListAdapter(profiles)
    }

    override fun addToProfilesList(
        profiles: List<GitHubProfile>
    ) {
        this.profiles.addAll(profiles)
        profiles_list.adapter?.notifyItemInserted(
            profiles_list.adapter?.itemCount?.minus(
                profiles.size
            )!!
        )
    }

    override fun showLoadError() {

    }

    override fun layoutResId(): Int {
        return R.layout.fragment_profiles_list
    }

}
