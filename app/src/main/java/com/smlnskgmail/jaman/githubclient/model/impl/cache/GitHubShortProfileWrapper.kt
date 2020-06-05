package com.smlnskgmail.jaman.githubclient.model.impl.cache

import com.google.gson.JsonObject
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

class GitHubShortProfileWrapper(
    private val gitHubShortProfile: GitHubShortProfile
) {

    companion object {

        private const val idKey = "key"
        private const val loginKey = "login"
        private const val typeKey = "type"
        private const val photoUrlKey = "photoUrl"

    }

    fun toJsonString(): String {
        val gitHubShortProfileJson = JsonObject()
        gitHubShortProfileJson.addProperty(
            idKey,
            gitHubShortProfile.id
        )
        gitHubShortProfileJson.addProperty(
            loginKey,
            gitHubShortProfile.login
        )
        gitHubShortProfileJson.addProperty(
            typeKey,
            gitHubShortProfile.type
        )
        if (gitHubShortProfile.photoUrl != null) {
            gitHubShortProfileJson.addProperty(
                photoUrlKey,
                gitHubShortProfile.photoUrl
            )
        }
        return gitHubShortProfileJson.toString()
    }

}
