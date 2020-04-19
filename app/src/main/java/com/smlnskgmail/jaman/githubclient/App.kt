package com.smlnskgmail.jaman.githubclient

import android.app.Application
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.github.impl.github.GitHubApi
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class App : Application() {

    private val appModule = Kodein.Module("App") {
        bind<GitHubProfilesApi>() with singleton { GitHubApi() }
    }

    val kodein = Kodein {
        import(appModule)
    }

}
