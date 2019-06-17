package com.jpardogo.patinetes.data.common.datasources

interface SharedPrefDataSource {
    fun delete(alias: String)
    fun <T> read(key: String, default: T): T
    fun exist(key: String): Boolean
    fun <T> write(key: String, value: T)
}