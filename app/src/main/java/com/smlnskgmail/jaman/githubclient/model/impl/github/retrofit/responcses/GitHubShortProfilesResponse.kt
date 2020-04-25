package com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses

import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubShortProfile

data class GitHubShortProfilesResponse(
    val shortProfiles: List<GitHubShortProfile>
)
