package com.smlnskgmail.jaman.githubclient.model.impl.cache

import com.google.gson.JsonParser
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

class GitHubShortProfileParser(
    private val content: String
) {

    companion object {

        private const val idKey = "id"
        private const val loginKey = "login"
        private const val typeKey = "type"
        private const val photoUrlKey = "photoUrl"

    }

    fun fromJsonString(): GitHubShortProfile {
        val gitHubShortProfileJson = JsonParser().parse(
            content
        ).asJsonObject
        val id = gitHubShortProfileJson.get(
            idKey
        ).asInt
        val login = gitHubShortProfileJson.get(
            loginKey
        ).asString
        val profileType = gitHubShortProfileJson.get(
            typeKey
        ).asString
        val photoUrl = if (gitHubShortProfileJson.has(photoUrlKey)) {
            gitHubShortProfileJson.get(
                photoUrlKey
            ).asString
        } else {
            null
        }
        return GitHubShortProfile(
            id,
            login,
            profileType,
            photoUrl
        )
    }

}
