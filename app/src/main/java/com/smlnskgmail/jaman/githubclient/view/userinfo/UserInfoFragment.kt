package com.smlnskgmail.jaman.githubclient.view.userinfo

import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.support.BaseFragment

class UserInfoFragment : BaseFragment(), UserInfoView {

    override fun layoutResId(): Int {
        return R.layout.fragment_user_info
    }

}
