package nl.jovmit.login

import nl.jovmit.login.data.LoginResponse

interface LoginDataSource {

    fun login(username: String, password: String): LoginResponse
}