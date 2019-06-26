package com.jpardogo.example.data.common

//Auth
const val HEADER_AUTHORIZATION = "Authorization"
/**
 * To be used for calls that need no auth automatically added via okHttp interceptor
 * No-Authorization : true
 * Reference: Making Retrofit Work For You by Jake Wharton - https://youtu.be/t34AQlblSeE?t=834
 */
const val HEADER_NO_AUTHORIZATION = "No-Authorization"
const val ACCESS_TOKEN = "ACCESS_TOKEN"
const val REFRESH_TOKEN = "REFRESH_TOKEN"