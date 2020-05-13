package com.smlnskgmail.jaman.githubclient.model.impl.github.official

import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.deserializers.OfficialGitHubExpandedProfileDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.deserializers.OfficialGitHubRepositoriesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.deserializers.OfficialGitHubShortProfilesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.OfficialGitHubApiService
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses.OfficialGitHubExpandedProfileResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses.OfficialGitHubRepositoriesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses.OfficialGitHubShortProfilesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OfficialGitHubApi :
    GitHubProfilesApi {

    private var retrofit: Retrofit

    private var officialGitHubApiService: OfficialGitHubApiService

    private val gson = GsonBuilder()
        .registerTypeAdapter(
            OfficialGitHubShortProfilesResponse::class.java,
            OfficialGitHubShortProfilesDeserializer()
        )
        .registerTypeAdapter(
            OfficialGitHubExpandedProfileResponse::class.java,
            OfficialGitHubExpandedProfileDeserializer()
        )
        .registerTypeAdapter(
            OfficialGitHubRepositoriesResponse::class.java,
            OfficialGitHubRepositoriesDeserializer()
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
        officialGitHubApiService = retrofit.create(
            OfficialGitHubApiService::class.java
        )
    }

    override fun profilesPortion(
        page: Int,
        profilesLoadCallback: GitHubProfilesApi.ProfilesLoadCallback
    ) {
        if (page == 1) {
            lastId = 0
        }
        officialGitHubApiService.profilesPortion(
            lastId
        ).enqueue(object : Callback<OfficialGitHubShortProfilesResponse> {
            override fun onFailure(
                call: Call<OfficialGitHubShortProfilesResponse>,
                t: Throwable
            ) {
                profilesLoadCallback.loadSuccess(
                    emptyList()
                )
            }

            override fun onResponse(
                call: Call<OfficialGitHubShortProfilesResponse>,
                response: Response<OfficialGitHubShortProfilesResponse>
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
        officialGitHubApiService.profile(
            login
        ).enqueue(object : Callback<OfficialGitHubExpandedProfileResponse> {
            override fun onFailure(
                call: Call<OfficialGitHubExpandedProfileResponse>,
                t: Throwable
            ) {
                expandedProfileLoadCallback.loadError()
            }

            override fun onResponse(
                call: Call<OfficialGitHubExpandedProfileResponse>,
                response: Response<OfficialGitHubExpandedProfileResponse>
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
        officialGitHubApiService.repositories(
            login,
            page
        ).enqueue(object : Callback<OfficialGitHubRepositoriesResponse> {
            override fun onFailure(
                call: Call<OfficialGitHubRepositoriesResponse>,
                t: Throwable
            ) {
                repositoriesLoadCallback.loadError()
            }

            override fun onResponse(
                call: Call<OfficialGitHubRepositoriesResponse>,
                response: Response<OfficialGitHubRepositoriesResponse>
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
