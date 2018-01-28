package nl.jovmit.login

import android.arch.lifecycle.LiveData
import nl.jovmit.login.data.LoginData
import nl.jovmit.login.data.LoginResponse

interface LoginDataSource {

    fun login(loginData: LoginData): LiveData<LoginResponse>
}