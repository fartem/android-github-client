package com.smlnskgmail.jaman.githubclient.entities

import com.smlnskgmail.jaman.githubclient.model.api.github.profiles.GitHubExpandedProfile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GitHubExpandedProfileTest : BaseEntityTest() {

    private val login = "fartem"
    private val name = "Artem Fomchenkov"
    private val email = "jaman.smlnsk@gmail.com"
    private val company = ""
    private val location = "Russia, Smolensk"
    private val photoUrl = "https://avatars0.githubusercontent.com/u/45129887?v=4"

    private val gitHubExpandedProfile = GitHubExpandedProfile(
        login,
        name,
        email,
        company,
        location,
        photoUrl
    )

    override fun `Validate fields`() {
        assertEquals(
            login,
            gitHubExpandedProfile.login
        )
        assertEquals(
            name,
            gitHubExpandedProfile.name
        )
        assertEquals(
            email,
            gitHubExpandedProfile.email
        )
        assertEquals(
            company,
            gitHubExpandedProfile.company
        )
        assertEquals(
            location,
            gitHubExpandedProfile.location
        )
        assertEquals(
            photoUrl,
            gitHubExpandedProfile.photoUrl
        )
    }

    override fun `Validate equals()`() {
        assertEquals(
            gitHubExpandedProfile,
            gitHubExpandedProfile
        )
        assertEquals(
            GitHubExpandedProfile(
                login,
                name,
                email,
                company,
                location,
                photoUrl
            ),
            gitHubExpandedProfile
        )

        assertNotEquals(
            GitHubExpandedProfile(
                "artem385",
                name,
                email,
                company,
                location,
                photoUrl
            ),
            gitHubExpandedProfile
        )
        assertNotEquals(
            GitHubExpandedProfile(
                login,
                "Artem",
                email,
                company,
                location,
                photoUrl
            ),
            gitHubExpandedProfile
        )
        assertNotEquals(
            GitHubExpandedProfile(
                login,
                name,
                "artem.fomchenkov@outlook.com",
                company,
                location,
                photoUrl
            ),
            gitHubExpandedProfile
        )
        assertNotEquals(
            GitHubExpandedProfile(
                login,
                name,
                email,
                company,
                "Smolensk, Russian Federation",
                photoUrl
            ),
            gitHubExpandedProfile
        )
        assertNotEquals(
            GitHubExpandedProfile(
                login,
                name,
                email,
                company,
                location,
                null
            ),
            gitHubExpandedProfile
        )
        assertNotEquals(
            gitHubExpandedProfile,
            null
        )
        assertNotEquals(
            gitHubExpandedProfile,
            "String"
        )
    }

    override fun `Validate hashCode()`() {
        assertEquals(
            gitHubExpandedProfile.hashCode(),
            gitHubExpandedProfile.hashCode()
        )
        assertEquals(
            GitHubExpandedProfile(
                login,
                name,
                email,
                company,
                location,
                photoUrl
            ).hashCode(),
            gitHubExpandedProfile.hashCode()
        )

        assertNotEquals(
            GitHubExpandedProfile(
                login,
                name,
                email,
                company,
                location,
                null
            ).hashCode(),
            gitHubExpandedProfile.hashCode()
        )
    }

}
