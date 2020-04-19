package com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit

import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubProfilesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubRepositoriesResponse
import retrofit2.Call

interface GitHubApiService {

    fun profilesPortion(
        offset: Int
    ): Call<GitHubProfilesResponse>

    fun repositories(
        profileId: String
    ): Call<GitHubRepositoriesResponse>

}
