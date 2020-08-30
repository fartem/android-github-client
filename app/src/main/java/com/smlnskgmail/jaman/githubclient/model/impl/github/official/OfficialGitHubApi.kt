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
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OfficialGitHubApi : GitHubProfilesApi {

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
            .addCallAdapterFactory(
                RxJava3CallAdapterFactory.create()
            )
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
        officialGitHubApiService.profilesPortion(lastId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { profilesLoadCallback.loadSuccess(it.shortProfiles) },
                { profilesLoadCallback.loadError() },
            )
    }

    override fun expandedProfileFor(
        login: String,
        expandedProfileLoadCallback: GitHubProfilesApi.ProfileLoadCallback
    ) {
        officialGitHubApiService.profile(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { expandedProfileLoadCallback.loadSuccess(it.expandedProfile) },
                { expandedProfileLoadCallback.loadError() },
            )
    }

    override fun repositoriesFor(
        login: String,
        page: Int,
        repositoriesLoadCallback: GitHubProfilesApi.RepositoriesLoadCallback
    ) {
        officialGitHubApiService.repositories(login, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { repositoriesLoadCallback.loadSuccess(it.repositories) },
                { repositoriesLoadCallback.loadError() },
            )
    }

}
