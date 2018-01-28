package nl.jovmit.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import nl.jovmit.R
import nl.jovmit.login.data.LoginData
import nl.jovmit.login.data.LoginResponse

class RemoteLoginDataSource : LoginDataSource {

    private val resultLiveData = MutableLiveData<LoginResponse>()

    override fun login(loginData: LoginData): LiveData<LoginResponse> {
        when {
            loginData.username.isBlank() -> resultLiveData.value = LoginResponse.Error(R.string.errorEmptyUsername)
            loginData.password.isBlank() -> resultLiveData.value = LoginResponse.Error(R.string.errorEmptyPassword)
            else -> performLogin()
        }
        return resultLiveData
    }

    private fun performLogin() {
        //TODO make some waiting (real network call)
        resultLiveData.postValue(LoginResponse.Success("accessToken"))
    }
}