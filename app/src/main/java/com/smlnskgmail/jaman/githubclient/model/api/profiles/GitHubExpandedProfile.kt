package com.smlnskgmail.jaman.githubclient.model.api.profiles

data class GitHubExpandedProfile(
    val login: String,
    val name: String,
    val company: String,
    val location: String,
    val email: String,
    val photoUrl: String?
)
