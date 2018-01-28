package nl.jovmit.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import nl.jovmit.R
import nl.jovmit.login.data.LoginData
import nl.jovmit.login.data.LoginResponse

internal class RemoteLoginDataSource : LoginDataSource {

    private val loginLiveData = MutableLiveData<LoginResponse>()

    override fun login(loginData: LoginData): LiveData<LoginResponse> {
        when {
            loginData.username.isBlank() ->
                loginLiveData.value = LoginResponse.Error(R.string.errorEmptyUsername)
            loginData.password.isBlank() ->
                loginLiveData.value = LoginResponse.Error(R.string.errorEmptyPassword)
            loginData.username != "user" && loginData.password != "pass" ->
                loginLiveData.value = LoginResponse.Error(R.string.errorInvalidLogin)
            else -> loginLiveData.value = LoginResponse.Success("accessToken")
        }
        return loginLiveData
    }
}