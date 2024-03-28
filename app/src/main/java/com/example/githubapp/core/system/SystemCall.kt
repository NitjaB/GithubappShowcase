package com.example.githubapp.core.system

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SystemCall @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun share(text: String) {
        context.startActivity(
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }
        )
    }
}