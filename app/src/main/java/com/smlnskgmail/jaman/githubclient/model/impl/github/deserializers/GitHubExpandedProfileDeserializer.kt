package com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubExpandedProfileResponse
import java.lang.reflect.Type

class GitHubExpandedProfileDeserializer : JsonDeserializer<GitHubExpandedProfileResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GitHubExpandedProfileResponse {
        TODO("Not yet implemented")
    }

}
