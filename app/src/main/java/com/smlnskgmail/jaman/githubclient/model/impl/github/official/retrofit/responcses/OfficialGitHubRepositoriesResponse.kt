package com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses

import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubRepository

data class OfficialGitHubRepositoriesResponse(
    val repositories: List<GitHubRepository>
)
