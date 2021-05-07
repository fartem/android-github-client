package com.smlnskgmail.jaman.githubclient.model.impl.cache

import android.content.Context
import com.orhanobut.hawk.Hawk
import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCache
import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCacheParameterTarget
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

class HawkAppCache(
    context: Context
) : AppCache {

    companion object {

        private const val firstProfileIdKey = "0"
        private const val secondProfileIdKey = "1"
        private const val thirdProfileIdKey = "2"
        private const val fourthProfileIdKey = "3"
        private const val fifthProfileIdKey = "4"

    }

    private val showedProfiles = mutableListOf<GitHubShortProfile>()

    private val showedProfilesTargets = mutableListOf<AppCacheParameterTarget>()

    init {
        Hawk.init(context)
            .build()
        if (Hawk.contains(firstProfileIdKey)) {
            Hawk.get<String>(firstProfileIdKey).let {
                showedProfiles.add(
                    GitHubShortProfileParser(it).fromJsonString()
                )
            }
        }
        if (Hawk.contains(secondProfileIdKey)) {
            Hawk.get<String>(secondProfileIdKey).let {
                showedProfiles.add(
                    GitHubShortProfileParser(it).fromJsonString()
                )
            }
        }
        if (Hawk.contains(thirdProfileIdKey)) {
            Hawk.get<String>(thirdProfileIdKey).let {
                showedProfiles.add(
                    GitHubShortProfileParser(it).fromJsonString()
                )
            }
        }
        if (Hawk.contains(fourthProfileIdKey)) {
            Hawk.get<String>(fourthProfileIdKey).let {
                showedProfiles.add(
                    GitHubShortProfileParser(it).fromJsonString()
                )
            }
        }
        if (Hawk.contains(fifthProfileIdKey)) {
            Hawk.get<String>(fifthProfileIdKey).let {
                showedProfiles.add(
                    GitHubShortProfileParser(it).fromJsonString()
                )
            }
        }
    }

    override fun saveShowedUser(gitHubShortProfile: GitHubShortProfile) {
        if (showedProfiles.contains(gitHubShortProfile)) {
            val profileIndex = showedProfiles.indexOf(
                gitHubShortProfile
            )
            showedProfiles.removeAt(profileIndex)
            Hawk.delete(profileIndex.toString())
        } else if (showedProfiles.size == AppCache.profilesMaxCount) {
            showedProfiles.removeAt(0)
            Hawk.delete(firstProfileIdKey)
        }
        showedProfiles.forEachIndexed { index, profile ->
            Hawk.put(
                index.toString(),
                GitHubShortProfileWrapper(
                    profile
                ).toJsonString()
            )
        }
        showedProfiles.add(gitHubShortProfile)
        Hawk.put(
            showedProfiles.size.minus(1).toString(),
            GitHubShortProfileWrapper(gitHubShortProfile).toJsonString()
        )
        showedProfilesTargets.forEach {
            it.updated()
        }
    }

    override fun showedUsers(): List<GitHubShortProfile> {
        return showedProfiles.reversed()
    }

    override fun addShowedUsersUpdateTarget(
        appCacheParameterTarget: AppCacheParameterTarget
    ) {
        showedProfilesTargets.add(appCacheParameterTarget)
    }

    override fun removeShowedUsersUpdateTarget(
        appCacheParameterTarget: AppCacheParameterTarget
    ) {
        showedProfilesTargets.remove(appCacheParameterTarget)
    }

}
