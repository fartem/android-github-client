package com.smlnskgmail.jaman.githubclient.model.impl.github.official.deserializers

import com.google.gson.*
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses.OfficialGitHubShortProfilesResponse
import java.lang.reflect.Type

class OfficialGitHubShortProfilesDeserializer : JsonDeserializer<OfficialGitHubShortProfilesResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        jsonDeserializationContext: JsonDeserializationContext?
    ): OfficialGitHubShortProfilesResponse {
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
        return OfficialGitHubShortProfilesResponse(
            profiles
        )
    }

}
