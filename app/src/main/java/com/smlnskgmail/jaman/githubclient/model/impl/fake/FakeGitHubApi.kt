package com.smlnskgmail.jaman.githubclient.model.impl.fake

import android.content.Context
import android.graphics.BitmapFactory
import com.smlnskgmail.jaman.githubclient.R
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
                "Artem Fomchenkov",
                "fartem",
                5,
                0,
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_person
                )
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
        offset: Int
    ): List<GitHubProfile> {
        return when {
            offset > profiles.size -> {
                hasUsers = false
                profiles
            }
            hasUsers -> profiles
            else -> emptyList()
        }
    }

    override fun repositoriesFor(
        profile: GitHubProfile
    ): List<GitHubRepository> {
        if (profile.name == "fartem") {
            return repositories
        }
        return emptyList()
    }

}
