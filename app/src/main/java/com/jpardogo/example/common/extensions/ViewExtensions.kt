package com.jpardogo.example.common.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.snackbar.Snackbar

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setIsVisible(isVisible: Boolean, keepSpace: Boolean = false) {
    if (isVisible) visible()
    else {
        if (keepSpace) {
            invisible()
        } else {
            gone()
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