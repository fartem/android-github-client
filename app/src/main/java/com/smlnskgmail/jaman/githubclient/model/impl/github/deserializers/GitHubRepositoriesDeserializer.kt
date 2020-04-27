package com.smlnskgmail.jaman.githubclient.model.impl.github.deserializers

import com.google.gson.*
import com.smlnskgmail.jaman.githubclient.model.api.GitHubRepository
import com.smlnskgmail.jaman.githubclient.model.impl.github.retrofit.responcses.GitHubRepositoriesResponse
import java.lang.reflect.Type

class GitHubRepositoriesDeserializer : JsonDeserializer<GitHubRepositoriesResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GitHubRepositoriesResponse {
        val repositories = mutableListOf<GitHubRepository>()
        (json as JsonArray).forEach {
            it as JsonObject

            val name = it.get("name").asString
            val description = it.get("description")
            val language = it.get("language")
            val stars = it.get("stargazers_count").asInt
            val forks = it.get("forks_count").asInt
            repositories.add(
                GitHubRepository(
                    name,
                    if (description.isJsonNull) "" else description.asString,
                    if (language.isJsonNull) "" else language.asString,
                    stars,
                    forks
                )
            )
        }
        return GitHubRepositoriesResponse(
            repositories
        )
    }

}
