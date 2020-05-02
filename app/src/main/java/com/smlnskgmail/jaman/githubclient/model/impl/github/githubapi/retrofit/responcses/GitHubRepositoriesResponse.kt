package com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.retrofit.responcses

import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubRepository

data class GitHubRepositoriesResponse(
    val repositories: List<GitHubRepository>
)
