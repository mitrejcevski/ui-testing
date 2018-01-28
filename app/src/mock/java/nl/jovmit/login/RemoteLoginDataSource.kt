package nl.jovmit.login

import nl.jovmit.R
import nl.jovmit.login.data.LoginResponse

internal class RemoteLoginDataSource : LoginDataSource {

    override fun login(username: String, password: String): LoginResponse {
        if (username.isBlank()) {
            return LoginResponse.Error(R.string.errorEmptyUsername)
        } else if (password.isBlank()) {
            return LoginResponse.Error(R.string.errorEmptyPassword)
        } else if (username != "user" && password != "pass") {
            return LoginResponse.Error(R.string.errorInvalidLogin)
        }
        return LoginResponse.Success("accessToken")
    }
}