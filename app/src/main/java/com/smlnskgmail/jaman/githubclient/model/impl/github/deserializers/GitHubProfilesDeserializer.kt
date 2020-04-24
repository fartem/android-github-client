package com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers

import com.google.gson.*
import com.smlnskgmail.jaman.githubclient.model.api.GitHubProfile
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubProfilesResponse
import java.lang.reflect.Type

class GitHubProfilesDeserializer : JsonDeserializer<GitHubProfilesResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        jsonDeserializationContext: JsonDeserializationContext?
    ): GitHubProfilesResponse {
        val profiles = mutableListOf<GitHubProfile>()
        (json as JsonArray).forEach {
            it as JsonObject
            profiles.add(
                GitHubProfile(
                    it.get("id").asString,
                    it.get("login").asString,
                    it.get("avatar_url").asString
                )
            )
        }
        return GitHubProfilesResponse(
            profiles
        )
    }

}
