package com.smlnskgmail.jaman.githubclient.model.api.cache

interface AppCache {

    companion object {

        const val profilesMaxCount = 5

    }

    fun saveShowedUserId(
        profileId: String
    )

    fun showedUsersIds(): List<String>

    fun addShowedUsersUpdateTarget(
        appCacheParameterTarget: AppCacheParameterTarget
    )
    fun removeShowedUsersUpdateTarget(
        appCacheParameterTarget: AppCacheParameterTarget
    )

}
