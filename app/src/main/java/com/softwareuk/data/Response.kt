package com.softwareuk.data

import androidx.annotation.StringRes

sealed class Response {
    class Success<T>(val response: T) : Response()
    class Error(@StringRes val errorRes: Int) : Response()
}