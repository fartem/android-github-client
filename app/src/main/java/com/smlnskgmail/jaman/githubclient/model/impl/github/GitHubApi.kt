package com.smlnskgmail.jaman.githubclient.model.impl.github

import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfile
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers.GitHubProfilesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers.GitHubRepositoriesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.GitHubApiService
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubProfilesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubRepositoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubApi : GitHubProfilesApi {

    private var retrofit: Retrofit

    private var gitHubApiService: GitHubApiService

    private val gson = GsonBuilder()
        .registerTypeAdapter(
            GitHubProfilesResponse::class.java,
            GitHubProfilesDeserializer()
        )
        .registerTypeAdapter(
            GitHubRepositoriesResponse::class.java,
            GitHubRepositoriesDeserializer()
        )
        .setLenient()
        .create()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(
                GsonConverterFactory.create(
                    gson
                )
            )
            .build()
        gitHubApiService = retrofit.create(
            GitHubApiService::class.java
        )
    }

    override fun profilesPortion(
        page: Int,
        profilesLoadCallback: GitHubProfilesApi.ProfilesLoadCallback
    ) {
        gitHubApiService.profilesPortion(
            page.times(30).toString()
        ).enqueue(object : Callback<GitHubProfilesResponse> {
            override fun onFailure(
                call: Call<GitHubProfilesResponse>,
                t: Throwable
            ) {
                profilesLoadCallback.loadSuccess(
                    emptyList()
                )
            }

            override fun onResponse(
                call: Call<GitHubProfilesResponse>,
                response: Response<GitHubProfilesResponse>
            ) {
                profilesLoadCallback.loadSuccess(
                    response.body()!!.profiles
                )
            }
        })
    }

    override fun repositoriesFor(
        profile: GitHubProfile,
        repositoriesLoadCallback: GitHubProfilesApi.RepositoriesLoadCallback
    ) {
        repositoriesLoadCallback.loadSuccess(
            emptyList()
        )
    }

}
