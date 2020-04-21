package com.smlnskgmail.jaman.githubclient

import android.app.Application
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.impl.fake.FakeGitHubApi
import com.smlnskgmail.jaman.githubclient.model.impl.github.GitHubApi
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    private val appModule = Kodein.Module("App") {
        bind<GitHubProfilesApi>() with singleton {
            @Suppress("ConstantConditionIf")
            if (BuildConfig.API_IMPL == "GITHUB") {
                GitHubApi()
            } else {
                FakeGitHubApi(
                    this@App
                )
            }
        }
    }

    override val kodeinTrigger = KodeinTrigger()

    @Suppress("SpellCheckingInspection")
    override val kodein = Kodein {
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()
        kodeinTrigger.trigger()
    }

}
