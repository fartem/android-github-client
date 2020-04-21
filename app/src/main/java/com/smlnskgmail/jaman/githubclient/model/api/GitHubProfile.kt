package com.smlnskgmail.jaman.githubclient.model.api

import android.graphics.Bitmap

data class GitHubProfile(
    val fullName: String,
    val name: String,
    val countOfPublicRepositories: Int,
    val countOfPublicGists: Int,
    val photo: Bitmap?
)
