package com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses

import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

data class OfficialGitHubShortProfilesResponse(
    val shortProfiles: List<GitHubShortProfile>
)
