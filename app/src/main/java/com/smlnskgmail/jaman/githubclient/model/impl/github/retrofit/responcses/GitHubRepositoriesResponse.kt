package com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses

import com.smlnskgmail.jaman.githubclient.model.api.GitHubRepository

data class GitHubRepositoriesResponse(
    val repositories: List<GitHubRepository>
)
