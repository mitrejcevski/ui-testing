package nl.jovmit.login

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import nl.jovmit.R
import nl.jovmit.any
import nl.jovmit.login.data.LoginResponse
import nl.jovmit.login.data.LoginViewModel
import nl.jovmit.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class LoginViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginObserver: Observer<LoginResponse>
    @Mock
    private lateinit var dataSource: LoginDataSource

    private val loginLiveData = MutableLiveData<LoginResponse>()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel(dataSource)
        whenever(dataSource.login(any())).thenReturn(loginLiveData)
    }

    @Test
    fun errorPushedFromDataSourceShouldGetDeliveredToObservers() {
        viewModel.login("user", "pass")
        viewModel.loginLiveData.observeForever(loginObserver)
        loginLiveData.value = LoginResponse.Error(R.string.errorEmptyUsername)
        verify(loginObserver).onChanged(LoginResponse.Error(R.string.errorEmptyUsername))
    }

    @Test
    fun successPushedFromDataSourceShouldGetDeliveredToObservers() {
        viewModel.login("user", "pass")
        viewModel.loginLiveData.observeForever(loginObserver)
        loginLiveData.value = LoginResponse.Success("accessToken")
        verify(loginObserver).onChanged(LoginResponse.Success("accessToken"))
    }
}