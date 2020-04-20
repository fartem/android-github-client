package com.smlnskgmail.jaman.githubclient

import android.app.Application
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.impl.fake.FakeGitHubApi
import com.smlnskgmail.jaman.githubclient.model.impl.github.GitHubApi
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class App : Application() {

    private val appModule = Kodein.Module("App") {
        bind<GitHubProfilesApi>() with singleton {
            @Suppress("ConstantConditionIf")
            if (BuildConfig.API_IMPL == "GITHUB") {
                GitHubApi()
            } else {
                FakeGitHubApi()
            }
        }
    }

    @Suppress("SpellCheckingInspection")
    val kodein = Kodein {
        import(appModule)
    }

}
