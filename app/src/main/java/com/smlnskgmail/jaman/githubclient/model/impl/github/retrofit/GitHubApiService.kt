package com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit

import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubProfilesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubRepositoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {

    @GET("/users?since={offset}")
    fun profilesPortion(
        @Path("offset") offset: Int
    ): Call<GitHubProfilesResponse>

    @GET("/users/{user}/repos")
    fun repositories(
        @Path("user") profileId: String
    ): Call<GitHubRepositoriesResponse>

}
