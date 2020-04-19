package com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubRepositoriesResponse
import java.lang.reflect.Type

class GitHubRepositoriesDeserializer : JsonDeserializer<GitHubRepositoriesResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GitHubRepositoriesResponse {
        TODO("Not yet implemented")
    }

}
