package nl.jovmit.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import nl.jovmit.R
import nl.jovmit.login.data.LoginData
import nl.jovmit.login.data.LoginResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class RemoteLoginDataSourceTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginObserver: Observer<LoginResponse>
    private lateinit var loginDataSource: LoginDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginDataSource = RemoteLoginDataSource()
    }

    @Test
    fun loginWithEmptyUsernameShouldReturnError() {
        loginDataSource.login(LoginData("", "")).observeForever(loginObserver)
        verify(loginObserver).onChanged(LoginResponse.Error(R.string.errorEmptyUsername))
    }

    @Test
    fun loginWithEmptyPasswordShouldReturnError() {
        loginDataSource.login(LoginData("username", "")).observeForever(loginObserver)
        verify(loginObserver).onChanged(LoginResponse.Error(R.string.errorEmptyPassword))
    }
}