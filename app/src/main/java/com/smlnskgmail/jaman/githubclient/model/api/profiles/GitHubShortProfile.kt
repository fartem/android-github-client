package com.smlnskgmail.jaman.githubclient.model.api.profiles

data class GitHubShortProfile(
    val login: String,
    val type: String,
    val photoUrl: String?
)
