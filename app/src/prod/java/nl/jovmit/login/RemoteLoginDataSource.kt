package nl.jovmit.login

import nl.jovmit.R
import nl.jovmit.login.data.LoginResponse

class RemoteLoginDataSource : LoginDataSource {

    override fun login(username: String, password: String): LoginResponse {
        if (username.isBlank()) {
            return LoginResponse.Error(R.string.errorEmptyUsername)
        } else if (password.isBlank()) {
            return LoginResponse.Error(R.string.errorEmptyPassword)
        }
        TODO("implementation missing to call real server")
    }
}