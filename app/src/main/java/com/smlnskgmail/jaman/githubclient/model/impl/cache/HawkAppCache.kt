package com.smlnskgmail.jaman.githubclient.model.impl.cache

import android.content.Context
import com.orhanobut.hawk.Hawk
import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCache
import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCacheParameterTarget
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

class HawkAppCache(
    context: Context
) : AppCache {

    private val showedProfiles = mutableListOf<GitHubShortProfile>()

    private val showedProfilesTargets = mutableListOf<AppCacheParameterTarget>()

    init {
        Hawk.init(context)
            .build()
        if (Hawk.contains("0")) {
            Hawk.get<String>("0").let {
                showedProfiles.add(
                    GitHubShortProfileParser.fromJson(
                        it
                    )
                )
            }
        }
        if (Hawk.contains("1")) {
            Hawk.get<String>("1").let {
                showedProfiles.add(
                    GitHubShortProfileParser.fromJson(
                        it
                    )
                )
            }
        }
        if (Hawk.contains("2")) {
            Hawk.get<String>("2").let {
                showedProfiles.add(
                    GitHubShortProfileParser.fromJson(
                        it
                    )
                )
            }
        }
        if (Hawk.contains("3")) {
            Hawk.get<String>("3").let {
                showedProfiles.add(
                    GitHubShortProfileParser.fromJson(
                        it
                    )
                )
            }
        }
        if (Hawk.contains("4")) {
            Hawk.get<String>("4").let {
                showedProfiles.add(
                    GitHubShortProfileParser.fromJson(
                        it
                    )
                )
            }
        }
    }

    override fun saveShowedUser(
        gitHubShortProfile: GitHubShortProfile
    ) {
        if (showedProfiles.size == AppCache.profilesMaxCount) {
            showedProfiles.removeAt(0)
            Hawk.delete("0")
            showedProfiles.forEachIndexed { index, profile ->
                Hawk.put(
                    index.toString(),
                    GitHubShortProfileParser.toJson(
                        profile
                    )
                )
            }
        }
        showedProfiles.add(
            gitHubShortProfile
        )
        Hawk.put(
            showedProfiles.size.minus(1).toString(),
            GitHubShortProfileParser.toJson(
                gitHubShortProfile
            )
        )
    }

    override fun showedUsers(): List<GitHubShortProfile> {
        return showedProfiles.reversed()
    }

    override fun addShowedUsersUpdateTarget(
        appCacheParameterTarget: AppCacheParameterTarget
    ) {
        showedProfilesTargets.add(
            appCacheParameterTarget
        )
    }

    override fun removeShowedUsersUpdateTarget(
        appCacheParameterTarget: AppCacheParameterTarget
    ) {
        showedProfilesTargets.remove(
            appCacheParameterTarget
        )
    }

}
