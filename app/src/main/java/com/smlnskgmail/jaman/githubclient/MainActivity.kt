package com.smlnskgmail.jaman.githubclient

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.smlnskgmail.jaman.githubclient.components.AppNavigator
import com.smlnskgmail.jaman.githubclient.components.BaseActivity
import com.smlnskgmail.jaman.githubclient.components.BaseFragment
import com.smlnskgmail.jaman.githubclient.view.profileinfo.ProfileInfoFragment
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), AppNavigator {

    private lateinit var toggle: ActionBarDrawerToggle

    private val fragmentsBackStackListener = FragmentManager.OnBackStackChangedListener {
        val currentFragment = supportFragmentManager.fragments.lastOrNull()
        if (currentFragment is BaseFragment) {
            Handler().post {
                main_toolbar.title = currentFragment.title()
            }
            if (currentFragment.enableBackPress()) {
                main_drawer.removeDrawerListener(toggle)
                main_drawer.setDrawerLockMode(
                    DrawerLayout.LOCK_MODE_LOCKED_CLOSED
                )
                toggle.setToolbarNavigationClickListener {
                    onBackPressed()
                }
                toggle.isDrawerIndicatorEnabled = false
                toggle.syncState()
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                toggle = ActionBarDrawerToggle(
                    this,
                    main_drawer,
                    main_toolbar,
                    R.string.app_name,
                    R.string.app_name
                )
                main_drawer.setDrawerLockMode(
                    DrawerLayout.LOCK_MODE_UNLOCKED
                )
                toggle.isDrawerIndicatorEnabled = true
                main_drawer.addDrawerListener(toggle)
                toggle.syncState()
            }
        }
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(main_toolbar)
        showFragment(ProfilesListFragment())
        initDrawerMenu()
    }

    private fun initDrawerMenu() {
        main_menu_header_version.text = BuildConfig.VERSION_NAME
        main_menu_search.
    }

    override fun onStart() {
        supportFragmentManager.addOnBackStackChangedListener(
            fragmentsBackStackListener
        )
        super.onStart()
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

    override fun showProfileInfoFor(
        profileId: String
    ) {
        val fragment = ProfileInfoFragment()
        val args = Bundle()
        args.putString(
            ProfileInfoFragment.profileIdKey,
            profileId
        )
        fragment.arguments = args
        showFragment(fragment)
    }

    override fun onStop() {
        supportFragmentManager.removeOnBackStackChangedListener(
            fragmentsBackStackListener
        )
        super.onStop()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}
