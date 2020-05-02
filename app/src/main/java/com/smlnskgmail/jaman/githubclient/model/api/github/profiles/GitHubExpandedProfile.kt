package com.smlnskgmail.jaman.githubclient.model.api.github.profiles

data class GitHubExpandedProfile(
    val login: String,
    val name: String,
    val email: String,
    val company: String,
    val location: String,
    val photoUrl: String?
)
