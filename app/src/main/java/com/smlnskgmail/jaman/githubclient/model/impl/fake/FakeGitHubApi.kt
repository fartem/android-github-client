package com.smlnskgmail.jaman.githubclient.model.impl.fake

import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.GitHubRepository
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubExpandedProfile
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubShortProfile

class FakeGitHubApi : GitHubProfilesApi {

    private val profiles = mutableListOf<GitHubShortProfile>()

    init {
        profiles.add(
            GitHubShortProfile(
                "fartem",
                "User",
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
    private var hasRepositories = true

    override fun profilesPortion(
        page: Int,
        profilesLoadCallback: GitHubProfilesApi.ProfilesLoadCallback
    ) {
        profilesLoadCallback.loadSuccess(
            when {
                page * 30 > profiles.size -> {
                    hasUsers = false
                    profiles
                }
                hasUsers -> profiles
                else -> emptyList()
            }
        )
    }

    override fun expandedProfileFor(
        login: String,
        expandedProfileLoadCallback: GitHubProfilesApi.ProfileLoadCallback
    ) {
        if (login == "fartem") {
            expandedProfileLoadCallback.loadSuccess(
                GitHubExpandedProfile(
                    "fartem",
                    "Artem Fomchenkov",
                    "",
                    "Russia, Smolensk",
                    "jaman.smlnsk@gmail.com",
                    null
                )
            )
        } else {
            expandedProfileLoadCallback.loadError()
        }
    }

    override fun repositoriesFor(
        login: String,
        page: Int,
        repositoriesLoadCallback: GitHubProfilesApi.RepositoriesLoadCallback
    ) {
        if (login == "fartem") {
            repositoriesLoadCallback.loadSuccess(
                when {
                    page * 30 > repositories.size -> {
                        hasRepositories = false
                        repositories
                    }
                    hasRepositories -> repositories
                    else -> emptyList()
                }
            )
        } else {
            repositoriesLoadCallback.loadError()
        }
    }

}
