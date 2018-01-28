package nl.jovmit.login.data

import android.support.annotation.StringRes

sealed class LoginResponse {

    data class Success(val accessToken: String) : LoginResponse()

    data class Error(@StringRes val resource: Int) : LoginResponse()
}