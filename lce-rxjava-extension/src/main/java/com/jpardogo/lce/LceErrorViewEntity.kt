package com.jpardogo.lce

import android.content.Context
import androidx.annotation.StringRes

@Suppress("UNUSED_PARAMETER")
class LceErrorViewEntity private constructor(
    errorContent: Any? = null, @StringRes private val stringResMessage: Int = NO_STRING_RES,
    private val stringMessage: String = ""
) {

    companion object {
        private const val NO_STRING_RES = -1
        fun <T> newInstance(
            errorContent: T? = null,
            @StringRes stringResMessage: Int = NO_STRING_RES,
            stringMessage: String = ""
        ) = LceErrorViewEntity(errorContent, stringResMessage, stringMessage)
    }

    fun getMessage(context: Context?) =
        if (stringResMessage != NO_STRING_RES) (context?.getString(stringResMessage)
            ?: "") else stringMessage
}

