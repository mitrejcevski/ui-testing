package nl.jovmit.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import nl.jovmit.R
import nl.jovmit.login.data.LoginResponse
import nl.jovmit.login.data.LoginViewModel
import nl.jovmit.main.MainActivity
import kotlin.LazyThreadSafetyMode.NONE

class LoginActivity : AppCompatActivity() {

    private val loginDataSource = RemoteLoginDataSource()
    private val factory = LoginViewModelFactory(loginDataSource)
    private val viewModel by lazy(NONE) {
        ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupLayout()
        observeViewModel()
    }

    private fun setupLayout() {
        supportActionBar?.setTitle(R.string.login)
        loginButton.setOnClickListener { performLogin() }
    }

    private fun performLogin() {
        val username = loginUsername.text.toString()
        val password = loginPassword.text.toString()
        viewModel.login(username, password)
    }

    private fun observeViewModel() {
        viewModel.loginLiveData.observe(this, Observer {
            it?.let {
                when (it) {
                    is LoginResponse.Error -> displayError(it)
                    is LoginResponse.Success -> openMainScreen()
                }
            }
        })
    }

    private fun displayError(result: LoginResponse.Error) {
        Snackbar.make(loginLayoutRoot, result.resource, Snackbar.LENGTH_SHORT).show()
    }

    private fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
