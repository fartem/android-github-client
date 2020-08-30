package com.smlnskgmail.jaman.githubclient.model.impl.github.official.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubExpandedProfile
import com.smlnskgmail.jaman.githubclient.model.impl.github.official.retrofit.responcses.OfficialGitHubExpandedProfileResponse
import java.lang.reflect.Type

class OfficialGitHubExpandedProfileDeserializer : JsonDeserializer<OfficialGitHubExpandedProfileResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): OfficialGitHubExpandedProfileResponse {
        json as JsonObject
        val name = json.get("name")
        val email = json.get("email")
        val company = json.get("company")
        val location = json.get("location")
        val gitHubExpandedProfile = GitHubExpandedProfile(
            json.get("login").asString,
            if (name.isJsonNull) "" else name.asString,
            if (email.isJsonNull) "" else email.asString,
            if (company.isJsonNull) "" else company.asString,
            if (location.isJsonNull) "" else location.asString,
            json.get("avatar_url").asString
        )
        return OfficialGitHubExpandedProfileResponse(
            gitHubExpandedProfile
        )
    }

}
