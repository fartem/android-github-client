package com.smlnskgmail.jaman.githubclient.model.api.github

data class GitHubRepository(
    val name: String,
    val description: String,
    val language: String,
    val stars: Int,
    val forks: Int
)
