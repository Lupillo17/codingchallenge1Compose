package com.example.codingchallenge1compose

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.ui.res.stringResource

object Utils {
    fun showError(context: Context) {
        Toast.makeText(context, context.getString(R.string.empty), Toast.LENGTH_SHORT).show()
    }
}