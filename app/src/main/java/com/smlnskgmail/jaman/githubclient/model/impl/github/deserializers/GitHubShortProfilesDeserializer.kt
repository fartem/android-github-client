package com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers

import com.google.gson.*
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubShortProfile
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubShortProfilesResponse
import java.lang.reflect.Type

class GitHubShortProfilesDeserializer : JsonDeserializer<GitHubShortProfilesResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        jsonDeserializationContext: JsonDeserializationContext?
    ): GitHubShortProfilesResponse {
        val profiles = mutableListOf<GitHubShortProfile>()
        (json as JsonArray).forEach {
            it as JsonObject
            profiles.add(
                GitHubShortProfile(
                    it.get("id").asInt,
                    it.get("login").asString,
                    it.get("type").asString,
                    it.get("avatar_url").asString
                )
            )
        }
        return GitHubShortProfilesResponse(
            profiles
        )
    }

}
