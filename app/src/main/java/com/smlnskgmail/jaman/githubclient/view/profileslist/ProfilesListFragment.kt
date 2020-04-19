package com.smlnskgmail.jaman.githubclient.view.profileslist

import com.smlnskgmail.jaman.githubclient.App
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.components.BaseFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class ProfilesListFragment : BaseFragment(), ProfilesListView, KodeinAware {

    override val kodein by Kodein.lazy {
        (context?.applicationContext as App).kodein
    }

    override fun layoutResId(): Int {
        return R.layout.fragment_profiles_list
    }

}
