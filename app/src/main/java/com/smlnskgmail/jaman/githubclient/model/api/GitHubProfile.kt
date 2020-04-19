package com.smlnskgmail.jaman.githubclient.model.api

import android.graphics.Bitmap

data class GitHubProfile(
    val name: String,
    val email: String,
    val countOfPublicRepositories: Int,
    val countOfPublicGists: Int,
    val photo: Bitmap
)
