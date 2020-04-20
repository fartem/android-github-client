package com.smlnskgmail.jaman.githubclient.components

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
    }

    abstract fun layoutResId(): Int

}
