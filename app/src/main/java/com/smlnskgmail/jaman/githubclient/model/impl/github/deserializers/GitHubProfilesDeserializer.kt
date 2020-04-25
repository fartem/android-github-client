package com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers

import com.google.gson.*
import com.smlnskgmail.jaman.githubclient.model.api.profiles.GitHubShortProfile
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubProfilesResponse
import java.lang.reflect.Type

class GitHubProfilesDeserializer : JsonDeserializer<GitHubProfilesResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        jsonDeserializationContext: JsonDeserializationContext?
    ): GitHubProfilesResponse {
        val profiles = mutableListOf<GitHubShortProfile>()
        (json as JsonArray).forEach {
            it as JsonObject
            profiles.add(
                GitHubShortProfile(
                    it.get("login").asString,
                    it.get("type").asString,
                    it.get("avatar_url").asString
                )
            )
        }
        return GitHubProfilesResponse(
            profiles
        )
    }

}
