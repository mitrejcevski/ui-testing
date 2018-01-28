package nl.jovmit.login

import nl.jovmit.R
import nl.jovmit.login.data.LoginResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RemoteLoginTest {

    private lateinit var loginDataSource: LoginDataSource

    @Before
    fun setUp() {
        loginDataSource = RemoteLoginDataSource()
    }

    @Test
    fun loginWithEmptyUsernameShouldReturnError() {
        val response = loginDataSource.login("", "")
        assertEquals(LoginResponse.Error(R.string.errorEmptyUsername), response)
    }

    @Test
    fun loginWithEmptyPasswordShouldReturnError() {
        val response = loginDataSource.login("username", "")
        assertEquals(LoginResponse.Error(R.string.errorEmptyPassword), response)
    }
}