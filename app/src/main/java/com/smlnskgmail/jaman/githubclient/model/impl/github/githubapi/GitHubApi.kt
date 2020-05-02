package com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi

import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.deserializers.GitHubExpandedProfileDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.deserializers.GitHubRepositoriesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.deserializers.GitHubShortProfilesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.retrofit.GitHubApiService
import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.retrofit.responcses.GitHubExpandedProfileResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.retrofit.responcses.GitHubRepositoriesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.githubapi.retrofit.responcses.GitHubShortProfilesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubApi :
    GitHubProfilesApi {

    private var retrofit: Retrofit

    private var gitHubApiService: GitHubApiService

    private val gson = GsonBuilder()
        .registerTypeAdapter(
            GitHubShortProfilesResponse::class.java,
            GitHubShortProfilesDeserializer()
        )
        .registerTypeAdapter(
            GitHubExpandedProfileResponse::class.java,
            GitHubExpandedProfileDeserializer()
        )
        .registerTypeAdapter(
            GitHubRepositoriesResponse::class.java,
            GitHubRepositoriesDeserializer()
        )
        .setLenient()
        .create()

    private var lastId = 0

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
        if (page == 1) {
            lastId = 0
        }
        gitHubApiService.profilesPortion(
            lastId
        ).enqueue(object : Callback<GitHubShortProfilesResponse> {
            override fun onFailure(
                call: Call<GitHubShortProfilesResponse>,
                t: Throwable
            ) {
                profilesLoadCallback.loadSuccess(
                    emptyList()
                )
            }

            override fun onResponse(
                call: Call<GitHubShortProfilesResponse>,
                response: Response<GitHubShortProfilesResponse>
            ) {
                if (response.body() != null) {
                    lastId = response.body()!!.shortProfiles.last().id
                    profilesLoadCallback.loadSuccess(
                        response.body()!!.shortProfiles
                    )
                } else {
                    profilesLoadCallback.loadError()
                }
            }
        })
    }

    override fun expandedProfileFor(
        login: String,
        expandedProfileLoadCallback: GitHubProfilesApi.ProfileLoadCallback
    ) {
        gitHubApiService.profile(
            login
        ).enqueue(object : Callback<GitHubExpandedProfileResponse> {
            override fun onFailure(
                call: Call<GitHubExpandedProfileResponse>,
                t: Throwable
            ) {
                expandedProfileLoadCallback.loadError()
            }

            override fun onResponse(
                call: Call<GitHubExpandedProfileResponse>,
                response: Response<GitHubExpandedProfileResponse>
            ) {
                if (response.body() != null) {
                    expandedProfileLoadCallback.loadSuccess(
                        response.body()!!.expandedProfile
                    )
                } else {
                    expandedProfileLoadCallback.loadError()
                }
            }
        })
    }

    override fun repositoriesFor(
        login: String,
        page: Int,
        repositoriesLoadCallback: GitHubProfilesApi.RepositoriesLoadCallback
    ) {
        gitHubApiService.repositories(
            login,
            page
        ).enqueue(object : Callback<GitHubRepositoriesResponse> {
            override fun onFailure(
                call: Call<GitHubRepositoriesResponse>,
                t: Throwable
            ) {
                repositoriesLoadCallback.loadError()
            }

            override fun onResponse(
                call: Call<GitHubRepositoriesResponse>,
                response: Response<GitHubRepositoriesResponse>
            ) {
                if (response.body() != null) {
                    repositoriesLoadCallback.loadSuccess(
                        response.body()!!.repositories
                    )
                } else {
                    repositoriesLoadCallback.loadError()
                }
            }
        })
    }

}
