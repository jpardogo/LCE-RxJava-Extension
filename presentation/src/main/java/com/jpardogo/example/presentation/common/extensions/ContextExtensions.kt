package com.jpardogo.example.presentation.common.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(
    stringMessage: String? = null, @StringRes resMessage: Int? = null,
    length: Int = Toast.LENGTH_SHORT
) {
    val text = stringMessage ?: getString(resMessage ?: return)
    Toast.makeText(this, text, length).show()
}