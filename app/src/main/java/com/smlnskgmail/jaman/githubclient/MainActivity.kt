package com.smlnskgmail.jaman.githubclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smlnskgmail.jaman.githubclient.components.AppNavigator
import com.smlnskgmail.jaman.githubclient.components.BaseActivity
import com.smlnskgmail.jaman.githubclient.view.profileinfo.ProfileInfoFragment
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListFragment

class MainActivity : BaseActivity(), AppNavigator {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        showFragment(ProfilesListFragment())
    }

    private fun showFragment(
        fragment: Fragment
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_container, fragment, "")
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun layoutResId(): Int {
        return R.layout.activity_main
    }

    override fun showProfileInfoFor(
        profileId: String
    ) {
        val fragment = ProfileInfoFragment()

        val args = Bundle()
        args.putString("profileId", profileId)
        fragment.arguments = args
        showFragment(fragment)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}
