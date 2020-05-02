package com.smlnskgmail.jaman.githubclient.model.impl.cache

import android.content.Context
import androidx.preference.PreferenceManager
import com.smlnskgmail.jaman.githubclient.model.api.cache.AppCache

class SharedPreferencesAppCache(
    context: Context
) : AppCache {

    companion object {

        private const val profilesCountKey = "profilesCount"

        private const val firstProfileKey = "firstProfile"
        private const val secondProfileKey = "secondProfile"
        private const val thirdProfileKey = "thirdProfile"
        private const val fourthProfileKey = "fourthProfile"
        private const val fifthProfileKey = "fifthProfile"

        private val profilesKeys = listOf(
            firstProfileKey,
            secondProfileKey,
            thirdProfileKey,
            fourthProfileKey,
            fifthProfileKey
        )

    }

    private var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
        context
    )
    private var profilesCount = sharedPreferences.getInt(
        profilesCountKey,
        0
    )

    private val showedProfilesIds = mutableListOf<String>()

    init {
        sharedPreferences.getString(
            firstProfileKey,
            null
        )?.let {
            showedProfilesIds.add(it)
        }
        sharedPreferences.getString(
            secondProfileKey,
            null
        )?.let {
            showedProfilesIds.add(it)
        }
        sharedPreferences.getString(
            thirdProfileKey,
            null
        )?.let {
            showedProfilesIds.add(it)
        }
        sharedPreferences.getString(
            fourthProfileKey,
            null
        )?.let {
            showedProfilesIds.add(it)
        }
        sharedPreferences.getString(
            fifthProfileKey,
            null
        )?.let {
            showedProfilesIds.add(it)
        }
    }

    override fun saveShowedUserId(
        profileId: String
    ) {
        showedProfilesIds.add(
            profileId
        )
        if (profilesCount < AppCache.profilesMaxCount) {
            sharedPreferences.edit()
                .putInt(
                    profilesCountKey,
                    ++profilesCount
                )
                .apply()
        } else {
            showedProfilesIds.removeAt(0)
        }
        sharedPreferences.edit()
            .putString(
                profilesKeys[showedProfilesIds.size - 1],
                showedProfilesIds.last()
            )
            .apply()
    }

    override fun showedUsersIds(): List<String> {
        return showedProfilesIds.reversed()
    }

}
