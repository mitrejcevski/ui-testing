package nl.jovmit.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import nl.jovmit.R
import nl.jovmit.login.data.LoginResponse
import nl.jovmit.main.MainActivity
import kotlin.LazyThreadSafetyMode.NONE

class LoginActivity : AppCompatActivity() {

    private val loginDataSource by lazy(NONE) { RemoteLoginDataSource() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupLayout()
    }

    private fun setupLayout() {
        supportActionBar?.setTitle(R.string.login)
        loginButton.setOnClickListener { performLogin() }
    }

    private fun performLogin() {
        val username = loginUsername.text.toString()
        val password = loginPassword.text.toString()
        val result = loginDataSource.login(username, password)
        when (result) {
            is LoginResponse.Error -> displayError(result)
            is LoginResponse.Success -> openMainScreen()
        }
    }

    private fun displayError(result: LoginResponse.Error) {
        Snackbar.make(loginLayoutRoot, result.resource, Snackbar.LENGTH_SHORT).show()
    }

    private fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
