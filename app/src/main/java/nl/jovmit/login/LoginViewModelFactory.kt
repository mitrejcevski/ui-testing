package nl.jovmit.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import nl.jovmit.login.data.LoginViewModel

class LoginViewModelFactory(private val dataSource: LoginDataSource) :
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(dataSource) as T
    }
}