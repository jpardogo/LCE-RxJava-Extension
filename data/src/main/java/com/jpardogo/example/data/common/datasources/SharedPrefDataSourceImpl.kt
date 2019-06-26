package com.jpardogo.example.data.common.datasources

import android.content.SharedPreferences
import androidx.core.content.edit

@Suppress("UNCHECKED_CAST")
class SharedPrefDataSourceImpl(private val prefs: SharedPreferences) : SharedPrefDataSource {

    override fun exist(key: String): Boolean = prefs.contains(key)

    override fun delete(alias: String) {
        prefs.edit {
            remove(alias)
            apply()
        }
    }

    override fun <T> read(key: String, default: T): T = when (default) {
        is String -> prefs.getString(key, default) as T
        is Int -> prefs.getInt(key, default) as T
        is Boolean -> prefs.getBoolean(key, default) as T
        is Long -> prefs.getLong(key, default) as T
        is Float -> prefs.getFloat(key, default) as T
        else -> throw IllegalArgumentException("Type mismatch")
    }

    override fun <T> write(key: String, value: T) {
        prefs.edit {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                else -> throw IllegalArgumentException("Type mismatch")
            }
            apply()
        }
    }
}