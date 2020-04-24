package com.smlnskgmail.jaman.githubclient.model.impl.github

import com.google.gson.GsonBuilder
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfile
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfilesApi
import com.smlnskgmail.jaman.githubclient.model.api.GitHubRepository
import com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers.GitHubProfilesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers.GitHubRepositoriesDeserializer
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.GitHubApiService
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubProfilesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubRepositoriesResponse
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
        offset: Int
    ): List<GitHubProfile> {
        TODO("Not yet implemented")
    }

    override fun repositoriesFor(
        profile: GitHubProfile
    ): List<GitHubRepository> {
        TODO("Not yet implemented")
    }

}
