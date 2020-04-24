package com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit

import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubProfilesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubRepositoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {

    @GET("/users")
    fun profilesPortion(
        @Query("since") offset: String
    ): Call<GitHubProfilesResponse>

    @GET("/users/{user}/repos")
    fun repositories(
        @Query("user") profileId: String
    ): Call<GitHubRepositoriesResponse>

}
