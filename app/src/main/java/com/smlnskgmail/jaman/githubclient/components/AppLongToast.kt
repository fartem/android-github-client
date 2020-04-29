package com.smlnskgmail.jaman.githubclient.components

import android.content.Context
import android.widget.Toast

class AppLongToast(
    private val context: Context,
    private val message: String
) {

    fun show() {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

}
