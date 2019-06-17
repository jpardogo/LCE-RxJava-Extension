package com.jpardogo.patinetes.data.common

interface AccessTokenProvider {
    fun getToken(): String
    fun getRefreshToken(): String
}