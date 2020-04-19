package com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubProfilesResponse
import java.lang.reflect.Type

class GitHubProfilesDeserializer : JsonDeserializer<GitHubProfilesResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GitHubProfilesResponse {
        TODO("Not yet implemented")
    }

}
