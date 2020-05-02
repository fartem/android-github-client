package com.smlnskgmail.jaman.githubclient.model.api.github.profiles

data class GitHubShortProfile(
    val id: Int,
    val login: String,
    val type: String,
    val photoUrl: String?
)
