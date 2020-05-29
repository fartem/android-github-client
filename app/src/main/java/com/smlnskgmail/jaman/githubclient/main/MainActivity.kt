package com.smlnskgmail.jaman.githubclient.main

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.smlnskgmail.jaman.githubclient.App
import com.smlnskgmail.jaman.githubclient.BuildConfig
import com.smlnskgmail.jaman.githubclient.R
import com.smlnskgmail.jaman.githubclient.components.AppNavigator
import com.smlnskgmail.jaman.githubclient.components.BaseActivity
import com.smlnskgmail.jaman.githubclient.components.BaseFragment
import com.smlnskgmail.jaman.githubclient.main.header.HeaderProfilesAdapter
import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCache
import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCacheParameterTarget
import com.smlnskgmail.jaman.githubclient.view.profileinfo.ProfileInfoFragment
import com.smlnskgmail.jaman.githubclient.view.profileslist.ProfilesListFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : BaseActivity(), AppNavigator, KodeinAware {

    override lateinit var kodein: Kodein

    private val appCache: AppCache by instance<AppCache>()
    private val showedUsersUpdateTarget = object : AppCacheParameterTarget {
        override fun updated() {
            (main_menu_search_results.adapter as HeaderProfilesAdapter).updateProfiles(
                appCache.showedUsers()
            )
        }
    }

    private lateinit var toggle: ActionBarDrawerToggle

    private val fragmentsBackStackListener = FragmentManager.OnBackStackChangedListener {
        var currentFragment = supportFragmentManager.fragments.lastOrNull()
        if (currentFragment !is BaseFragment) {
            currentFragment = supportFragmentManager.fragments.firstOrNull()
        }
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
        kodein = (applicationContext as App).kodein
        showFragment(ProfilesListFragment())
        initDrawerMenu()
    }

    private fun initDrawerMenu() {
        main_menu_header_version.text = BuildConfig.VERSION_NAME
        main_menu_search_results.messageView = main_menu_search_results_error
        main_menu_search_results.layoutManager = LinearLayoutManager(this)
        main_menu_search_results.adapter = HeaderProfilesAdapter(
            appCache.showedUsers()
        )
    }

    override fun onStart() {
        supportFragmentManager.addOnBackStackChangedListener(
            fragmentsBackStackListener
        )
        appCache.addShowedUsersUpdateTarget(
            showedUsersUpdateTarget
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
        appCache.removeShowedUsersUpdateTarget(
            showedUsersUpdateTarget
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
