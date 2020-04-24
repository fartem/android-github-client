package com.smlnskgmail.jaman.githubclient.model.impl.fake

import android.content.Context
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfile
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.GitHubRepository

class FakeGitHubApi(
    private val context: Context
) : GitHubProfilesApi {

    private val profiles = mutableListOf<GitHubProfile>()

    init {
        profiles.add(
            GitHubProfile(
                Integer.MAX_VALUE.toString(),
                "fartem",
                null
            )
        )
    }

    private val repositories = listOf(
        GitHubRepository(
            "hash-checker",
            "Fast and simple application for generating and comparison hashes from files or text",
            "Java",
            19,
            5
        ),
        GitHubRepository(
            "crypto-tracker",
            "Demo application with statistics of some cryptocurrencies",
            "Kotlin, Java",
            8,
            1
        ),
        GitHubRepository(
            "android-device-info",
            "Demo app for displaying some information about Android device",
            "Kotlin, Java",
            10,
            4
        ),
        GitHubRepository(
            "parse-android-test-app",
            "Demo Android application for Parse test server. Server: https://github.com/fartem/parse-test-server",
            "Kotlin, Java",
            13,
            3
        ),
        GitHubRepository(
            "parse-test-server",
            "Parse server for https://github.com/fartem/parse-android-test-app",
            "JavaScript",
            4,
            2
        )
    )

    private var hasUsers = true

    override fun profilesPortion(
        page: Int,
        profilesLoadCallback: GitHubProfilesApi.ProfilesLoadCallback
    ) {
        profilesLoadCallback.loadSuccess(
            when {
                page > profiles.size -> {
                    hasUsers = false
                    profiles
                }
                hasUsers -> profiles
                else -> emptyList()
            }
        )
    }

    override fun repositoriesFor(
        profile: GitHubProfile,
        repositoriesLoadCallback: GitHubProfilesApi.RepositoriesLoadCallback
    ) {
        repositoriesLoadCallback.loadSuccess(
            if (profile.login == "fartem") {
                repositories
            } else {
                emptyList()
            }
        )
    }

}
