package com.smlnskgmail.jaman.githubclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smlnskgmail.jaman.githubclient.components.BaseActivity
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListFragment

class MainActivity : BaseActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        showFragment(ProfilesListFragment())
    }

    private fun showFragment(
        fragment: Fragment
    ) {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, fragment, "")
            .addToBackStack(null)
            .commit()
    }

    override fun layoutResId(): Int {
        return R.layout.activity_main
    }

}
