package nl.jovmit.login.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import nl.jovmit.login.LoginDataSource
import kotlin.LazyThreadSafetyMode.NONE

class LoginViewModel(private val dataSource: LoginDataSource) : ViewModel() {

    private val loginData = MutableLiveData<LoginData>()
    private val loginResponse = switchMap { dataSource.login(it) }

    val loginLiveData by lazy(NONE) {
        Transformations.map(loginResponse) { it }
    }

    fun login(username: String, password: String) {
        loginData.value = LoginData(username, password)
    }

    private fun <T> switchMap(block: (LoginData) -> (LiveData<T>)): LiveData<T> {
        return Transformations.switchMap(loginData) {
            block(it)
        }
    }
}