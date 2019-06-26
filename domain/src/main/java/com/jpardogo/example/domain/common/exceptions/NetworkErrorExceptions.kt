package com.jpardogo.example.domain.common.exceptions

import java.net.HttpURLConnection

sealed class RemoteException(code: Int, message: String) : RuntimeException("HTTP $code $message")
class NetworkException(code: Int, message: String) : RemoteException(code, message)
class NotLoggedInException(message: String) : RemoteException(HttpURLConnection.HTTP_UNAUTHORIZED, message)