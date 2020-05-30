package com.smlnskgmail.jaman.githubclient.model.impl.cache

import com.google.gson.JsonObject
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

class GitHubShortProfileWrapper(
    private val gitHubShortProfile: GitHubShortProfile
) {

    fun toJsonString(): String {
        val gitHubShortProfileJson = JsonObject()
        gitHubShortProfileJson.addProperty(
            "id",
            gitHubShortProfile.id
        )
        gitHubShortProfileJson.addProperty(
            "login",
            gitHubShortProfile.login
        )
        gitHubShortProfileJson.addProperty(
            "type",
            gitHubShortProfile.type
        )
        if (gitHubShortProfile.photoUrl != null) {
            gitHubShortProfileJson.addProperty(
                "photoUrl",
                gitHubShortProfile.photoUrl
            )
        }
        return gitHubShortProfileJson.toString()
    }

}
