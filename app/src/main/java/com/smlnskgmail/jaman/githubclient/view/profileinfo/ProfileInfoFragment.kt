package com.smlnskgmail.jaman.githubclient.view.profileinfo

import com.smlnskgmail.jaman.githubclient.App
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.components.BaseFragment
import org.kodein.di.KodeinAware

class ProfileInfoFragment : BaseFragment(), ProfileInfoView, KodeinAware {

    override val kodein by lazy {
        (context?.applicationContext as App).kodein
    }

    override fun layoutResId(): Int {
        return R.layout.fragment_profile_info
    }

}
