package com.smlnskgmail.jaman.githubclient.model.impl.cache

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile

class GitHubShortProfileParser {

    companion object {

        fun fromJson(
            content: String
        ): GitHubShortProfile {
            val gitHubShortProfileJson = JsonParser().parse(
                content
            ).asJsonObject
            val id = gitHubShortProfileJson.get(
                "id"
            ).asInt
            val login = gitHubShortProfileJson.get(
                "login"
            ).asString
            val profileType = gitHubShortProfileJson.get(
                "type"
            ).asString
            val photoUrl = if (gitHubShortProfileJson.has("photoUrl")) {
                gitHubShortProfileJson.get(
                    "photoUrl"
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

        fun toJson(
            gitHubShortProfile: GitHubShortProfile
        ): String {
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

}
