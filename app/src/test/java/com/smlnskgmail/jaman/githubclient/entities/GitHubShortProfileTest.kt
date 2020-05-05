package com.smlnskgmail.jaman.githubclient.entities

import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubShortProfile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals

class GitHubShortProfileTest : BaseEntityTest() {

    private val id = 45129887
    private val login = "fartem"
    private val type = "User"
    private val photoUrl = "https://avatars0.githubusercontent.com/u/45129887?v=4"

    private val gitHubShortProfile = GitHubShortProfile(
        id,
        login,
        type,
        photoUrl
    )

    override fun `Validate fields`() {
        assertEquals(
            id,
            gitHubShortProfile.id
        )
        assertEquals(
            login,
            gitHubShortProfile.login
        )
        assertEquals(
            type,
            gitHubShortProfile.type
        )
        assertEquals(
            photoUrl,
            gitHubShortProfile.photoUrl
        )
    }

    override fun `Validate equals()`() {
        assertEquals(
            gitHubShortProfile,
            gitHubShortProfile
        )
        assertEquals(
            GitHubShortProfile(
                id,
                login,
                type,
                photoUrl
            ),
            gitHubShortProfile
        )

        assertNotEquals(
            GitHubShortProfile(
                57216054,
                login,
                type,
                photoUrl
            ),
            gitHubShortProfile
        )
        assertNotEquals(
            GitHubShortProfile(
                id,
                "artem385",
                type,
                photoUrl
            ),
            gitHubShortProfile
        )
        assertNotEquals(
            GitHubShortProfile(
                id,
                login,
                "Bot",
                photoUrl
            ),
            gitHubShortProfile
        )
        assertNotEquals(
            GitHubShortProfile(
                id,
                login,
                type,
                null
            ),
            gitHubShortProfile
        )
        assertNotEquals(
            gitHubShortProfile,
            null
        )
        assertNotEquals(
            gitHubShortProfile,
            "String"
        )
    }

    override fun `Validate hashCode()`() {
        assertEquals(
            gitHubShortProfile.hashCode(),
            gitHubShortProfile.hashCode()
        )
        assertEquals(
            GitHubShortProfile(
                id,
                login,
                type,
                photoUrl
            ).hashCode(),
            gitHubShortProfile.hashCode()
        )

        assertNotEquals(
            GitHubShortProfile(
                id,
                login,
                type,
                null
            ).hashCode(),
            gitHubShortProfile.hashCode()
        )
    }

}
