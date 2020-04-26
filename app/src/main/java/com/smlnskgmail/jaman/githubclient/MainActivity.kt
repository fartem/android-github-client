package com.smlnskgmail.jaman.githubclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smlnskgmail.jaman.githubclient.components.BaseActivity
import com.smlnskgmail.jaman.githubclient.view.profileinfo.ProfileInfoFragment

class MainActivity : BaseActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        val fragment = ProfileInfoFragment()

        val args = Bundle()
        args.putString("profileId", "fartem")
        fragment.arguments = args
        showFragment(fragment)
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
