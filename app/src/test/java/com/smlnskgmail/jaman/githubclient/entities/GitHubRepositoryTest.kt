package com.smlnskgmail.jaman.githubclient.entities

import com.smlnskgmail.jaman.githubclient.model.api.github.GitHubRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class GitHubRepositoryTest : BaseEntityTest() {

    private val name = "hash-checker"
    private val description = "Fast and simple application for generating and comparison hashes from files or text"
    private val language = "Java"
    private val stars = 19
    private val forks = 5

    private val gitHubRepository = GitHubRepository(
        name,
        description,
        language,
        stars,
        forks
    )

    override fun `Validate fields`() {
        assertEquals(
            name,
            gitHubRepository.name
        )
        assertEquals(
            description,
            gitHubRepository.description
        )
        assertEquals(
            language,
            gitHubRepository.language
        )
        assertEquals(
            stars,
            gitHubRepository.stars
        )
        assertEquals(
            forks,
            gitHubRepository.forks
        )
    }

    override fun `Validate equals()`() {
        assertEquals(
            gitHubRepository,
            gitHubRepository
        )
        assertEquals(
            GitHubRepository(
                name,
                description,
                language,
                stars,
                forks
            ),
            gitHubRepository
        )

        assertNotEquals(
            GitHubRepository(
                "android-hash-checker",
                description,
                language,
                stars,
                forks
            ),
            gitHubRepository
        )
        assertNotEquals(
            GitHubRepository(
                name,
                "Fastapplication for generating and comparison hashes from files or text",
                language,
                stars,
                forks
            ),
            gitHubRepository
        )
        assertNotEquals(
            GitHubRepository(
                name,
                description,
                "Kotlin",
                stars,
                forks
            ),
            gitHubRepository
        )
        assertNotEquals(
            GitHubRepository(
                name,
                description,
                language,
                11,
                forks
            ),
            gitHubRepository
        )
        assertNotEquals(
            GitHubRepository(
                name,
                description,
                language,
                stars,
                1
            ),
            gitHubRepository
        )
        assertNotEquals(
            gitHubRepository,
            null
        )
        assertNotEquals(
            gitHubRepository,
            "String"
        )
    }

    override fun `Validate hashCode()`() {
        assertEquals(
            gitHubRepository.hashCode(),
            gitHubRepository.hashCode()
        )
        assertEquals(
            GitHubRepository(
                name,
                description,
                language,
                stars,
                forks
            ).hashCode(),
            gitHubRepository.hashCode()
        )

        assertNotEquals(
            GitHubRepository(
                "android-hash-checker",
                description,
                language,
                stars,
                forks
            ).hashCode(),
            gitHubRepository.hashCode()
        )
    }

}
