package com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit

import com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses.OfficialGitHubExpandedProfileResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses.OfficialGitHubRepositoriesResponse
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses.OfficialGitHubShortProfilesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OfficialGitHubApiService {

    @GET("/users")
    fun profilesPortion(
        @Query("since") offset: Int
    ): Observable<OfficialGitHubShortProfilesResponse>

    @GET("/users/{user}")
    fun profile(
        @Path("user") profileId: String
    ): Observable<OfficialGitHubExpandedProfileResponse>

    @GET("/users/{user}/repos")
    fun repositories(
        @Path("user") profileId: String,
        @Query("page") page: Int
    ): Observable<OfficialGitHubRepositoriesResponse>

}
