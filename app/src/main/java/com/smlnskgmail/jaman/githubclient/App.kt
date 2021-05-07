package com.smlnskgmail.jaman.githubclient

import android.app.Application
import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCache
import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.impl.cache.HawkAppCache
import com.smlnskgmail.jaman.githubclient.model.impl.github.fake.FakeGitHubApi
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.OfficialGitHubApi
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
                OfficialGitHubApi()
            } else {
                FakeGitHubApi()
            }
        }
        bind<AppCache>() with singleton {
            HawkAppCache(this@App)
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
