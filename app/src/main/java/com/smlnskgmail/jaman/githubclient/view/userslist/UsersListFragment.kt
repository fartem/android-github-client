package com.smlnskgmail.jaman.githubclient.view.userslist

import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.support.BaseFragment

class UsersListFragment : BaseFragment(), UsersListView {

    override fun layoutResId(): Int {
        return R.layout.fragment_users_list
    }

}
