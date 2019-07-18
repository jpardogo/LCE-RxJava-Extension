package com.jpardogo.example.presentation.common.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar

fun View.setVisibility(visible: Boolean, keepSpace: Boolean) {
    if (visible) isVisible = true
    else {
        if (keepSpace) {
            isInvisible = true
        } else {
            isGone = true
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun View.snack(
    message: String,
    actionText: String? = "Action",
    length: Int? = null,
    actionListener: ((View) -> Unit)? = null
) = Snackbar.make(
    this,
    message,
    when {
        length != null -> length
        actionListener != null -> Snackbar.LENGTH_LONG
        else -> Snackbar.LENGTH_SHORT
    }
).apply {
    actionListener?.let {
        setAction(actionText, actionListener)
    }
}.also {
    it.show()
}