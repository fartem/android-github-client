package com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.retrofit

import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.retrofit.responcses.GitHubExpandedProfileResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.retrofit.responcses.GitHubRepositoriesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.retrofit.responcses.GitHubShortProfilesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {

    @GET("/users")
    fun profilesPortion(
        @Query("since") offset: Int
    ): Call<GitHubShortProfilesResponse>

    @GET("/users/{user}")
    fun profile(
        @Path("user") profileId: String
    ): Call<GitHubExpandedProfileResponse>

    @GET("/users/{user}/repos")
    fun repositories(
        @Path("user") profileId: String,
        @Query("page") page: Int
    ): Call<GitHubRepositoriesResponse>

}
