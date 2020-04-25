package com.smlnskgmail.jaman.githubclient.model.impl.github

import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers.GitHubExpandedProfileDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers.GitHubRepositoriesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers.GitHubShortProfilesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.GitHubApiService
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubExpandedProfileResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubRepositoriesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubShortProfilesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubApi : GitHubProfilesApi {

    companion object {

        private const val itemsInPage = 30

    }

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
        if (page == 0) {
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
                responseShort: Response<GitHubShortProfilesResponse>
            ) {
                lastId = responseShort.body()!!.shortProfiles.last().id
                profilesLoadCallback.loadSuccess(
                    responseShort.body()!!.shortProfiles
                )
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
                expandedProfileLoadCallback.loadSuccess(
                    response.body()!!.expandedProfile
                )
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
            page.times(itemsInPage)
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
                repositoriesLoadCallback.loadSuccess(
                    response.body()!!.repositories
                )
            }
        })
    }

}
