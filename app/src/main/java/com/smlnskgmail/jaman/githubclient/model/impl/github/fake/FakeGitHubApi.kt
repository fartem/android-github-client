package com.smlnskgmail.jaman.githubclient.model.impl.github.fake

import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubRepository
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubExpandedProfile
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

@SuppressWarnings(
    "StringLiteralDuplication",
    "MagicNumber"
)
class FakeGitHubApi :
    GitHubProfilesApi {

    private val profiles = mutableListOf<GitHubShortProfile>()

    init {
        profiles.add(
            GitHubShortProfile(
                -1,
                "fartem",
                "User",
                null
            )
        )
        profiles.add(
            GitHubShortProfile(
                -1,
                "artem385",
                "User",
                null
            )
        )
        profiles.add(
            GitHubShortProfile(
                -1,
                "bot385",
                "Bot",
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
            "Kotlin",
            8,
            1
        ),
        GitHubRepository(
            "android-device-info",
            "Demo app for displaying some information about Android device",
            "Kotlin",
            10,
            4
        ),
        GitHubRepository(
            "parse-android-test-app",
            "Demo Android application for Parse test server. Server: https://github.com/fartem/parse-test-server",
            "Kotlin",
            13,
            3
        ),
        GitHubRepository(
            "parse-test-server",
            "Parse server for https://github.com/fartem/parse-android-test-app",
            "JavaScript",
            4,
            2
        ),
        GitHubRepository(
            "android-remote-temperature-control-client",
            "Remote client for https://github.com/fartem/arduino-temperature-control",
            "Kotlin",
            12,
            4
        ),
        GitHubRepository(
            "arduino-temperature-control",
            "Temperature monitoring project. Android app: https://github.com/fartem/android-remote-temperature-control-client",
            "C++",
            2,
            0
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
                page == 1 -> {
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
        when (login) {
            "fartem" -> {
                expandedProfileLoadCallback.loadSuccess(
                    GitHubExpandedProfile(
                        "fartem",
                        "Artem Fomchenkov",
                        "jaman.smlnsk@gmail.com",
                        "",
                        "Smolensk, Russia",
                        null
                    )
                )
            }
            "artem385" -> {
                expandedProfileLoadCallback.loadSuccess(
                    GitHubExpandedProfile(
                        "artem385",
                        "Artem",
                        "artem.fomchenkov@outlook.com",
                        "",
                        "Smolensk, Russia",
                        null
                    )
                )
            }
            "bot385" -> {
                expandedProfileLoadCallback.loadSuccess(
                    GitHubExpandedProfile(
                        "bot385",
                        "Bot",
                        "",
                        "",
                        "",
                        null
                    )
                )
            }
            else -> {
                expandedProfileLoadCallback.loadError()
            }
        }
    }

    override fun repositoriesFor(
        login: String,
        page: Int,
        repositoriesLoadCallback: GitHubProfilesApi.RepositoriesLoadCallback
    ) {
        when (login) {
            "fartem" -> {
                repositoriesLoadCallback.loadSuccess(
                    when {
                        page == 1 -> {
                            hasRepositories = false
                            repositories
                        }
                        hasRepositories -> repositories
                        else -> emptyList()
                    }
                )
            }
            "artem385" -> {
                repositoriesLoadCallback.loadSuccess(
                    emptyList()
                )
            }
            "bot385" -> {
                repositoriesLoadCallback.loadSuccess(
                    emptyList()
                )
            }
            else -> {
                repositoriesLoadCallback.loadError()
            }
        }
    }

}
