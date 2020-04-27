package com.smlnskgmail.jaman.githubclient.view.profileinfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.JsonParser
import com.smlnskgmail.jaman.githubclient.App
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.components.BaseFragment
import com.smlnskgmail.jaman.githubclient.components.recyclerview.ExpandableRecyclerViewPagination
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.GitHubRepository
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubExpandedProfile
import com.smlnskgmail.jaman.githubclient.presenter.profileinfo.ProfileInfoPresenter
import com.smlnskgmail.jaman.githubclient.presenter.profileinfo.ProfileInfoPresenterImpl
import kotlinx.android.synthetic.main.fragment_profile_info.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.io.BufferedReader
import java.io.InputStreamReader

class ProfileInfoFragment : BaseFragment(), ProfileInfoView, KodeinAware {

    override val kodein by lazy {
        (context?.applicationContext as App).kodein
    }

    private val gitHubProfilesApi: GitHubProfilesApi by instance<GitHubProfilesApi>()

    private lateinit var profileInfoPresenter: ProfileInfoPresenter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        profileInfoPresenter = ProfileInfoPresenterImpl()
        profileInfoPresenter.init(
            gitHubProfilesApi,
            this,
            arguments!!.getString(
                "profileId",
            null
            )
        )
    }

    @SuppressLint("CheckResult")
    override fun showProfile(
        expandedProfile: GitHubExpandedProfile
    ) {
        profile_progress_bar.visibility = View.GONE
        profile_panel.visibility = View.VISIBLE

        profile_login.text = expandedProfile.login
        if (expandedProfile.name.isNotEmpty()) {
            profile_name.text = expandedProfile.name
        } else {
            profile_company.visibility = View.GONE
        }
        if (expandedProfile.company.isNotEmpty()) {
            profile_company.text = expandedProfile.company
        } else {
            profile_company.visibility = View.GONE
        }
        if (expandedProfile.location.isNotEmpty()) {
            profile_location.text = expandedProfile.location
        } else {
            profile_location.visibility = View.GONE
        }
        if (expandedProfile.photoUrl != null) {
            val requestOptions = RequestOptions()
            requestOptions.override(150, 150)

            Glide.with(context!!)
                .asBitmap()
                .apply(requestOptions)
                .load(expandedProfile.photoUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(profile_avatar)
        } else {
            profile_avatar.setImageResource(
                R.drawable.ic_profile
            )
        }
    }

    override fun showRepositories(
        gitHubRepositories: List<GitHubRepository>
    ) {
        repositories_progress_bar.visibility = View.GONE
        repositories_list.visibility = View.VISIBLE

        val layoutManager = LinearLayoutManager(context)
        repositories_list.layoutManager = layoutManager
        repositories_list.addOnScrollListener(
            object : ExpandableRecyclerViewPagination(
                layoutManager
            ) {
                override fun loadMoreItems() {
                    profileInfoPresenter.loadMoreRepositories()
                }

                override fun isLastPage(): Boolean {
                    return profileInfoPresenter.isLastPage()
                }

                override fun isLoading(): Boolean {
                    return profileInfoPresenter.repositoriesLoading()
                }
            }
        )

        val bufferedReader = BufferedReader(
            InputStreamReader(
                resources.openRawResource(
                    R.raw.languages
                )
            )
        )
        val languages = JsonParser().parse(
            bufferedReader
        ).asJsonObject
        repositories_list.adapter = RepositoriesListAdapter(
            gitHubRepositories,
            languages
        )
    }

    override fun addToRepositoriesList(
        gitHubRepositories: List<GitHubRepository>
    ) {
        (repositories_list.adapter as RepositoriesListAdapter).addMore(
            gitHubRepositories
        )
    }

    override fun showLoadError() {

    }

    override fun layoutResId(): Int {
        return R.layout.fragment_profile_info
    }

}
