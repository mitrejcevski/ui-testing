package nl.jovmit.login

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import nl.jovmit.login.LoginTestRobot.Companion.loginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun shouldApplyCorrectTitle() {
        loginScreen {
            hasLoginTitle()
        }
    }

    @Test
    fun shouldContainUsernameInput() {
        loginScreen {
            includesUsername()
        }
    }

    @Test
    fun usernameInputShouldApplyCorrectHint() {
        loginScreen {
            usernameEditor {
                hasCorrectHint()
            }
        }
    }

    @Test
    fun shouldContainPasswordInput() {
        loginScreen {
            includesPassword()
        }
    }

    @Test
    fun passwordInputShouldApplyCorrectHint() {
        loginScreen {
            passwordEditor {
                hasCorrectHint()
            }
        }
    }

    @Test
    fun shouldContainLoginButton() {
        loginScreen {
            includesLoginButton()
        }
    }

    @Test
    fun loginButtonShouldApplyCorrectText() {
        loginScreen {
            loginButton {
                hasCorrectTitle()
            }
        }
    }

    @Test
    fun loginWithoutUsernameShouldDisplayError() {
        loginScreen {
            typeUsername("")
            typePassword("")
        } submit {
            displaysEmptyUsernameError()
        }
    }

    @Test
    fun loginWithEmptyPasswordShouldDisplayError() {
        loginScreen {
            typeUsername("username")
            typePassword("")
        } submit {
            displaysEmptyPasswordError()
        }
    }

    @Test
    fun loginWithIncorrectCredentialsShouldDisplayError() {
        loginScreen {
            typeUsername("username")
            typePassword("password")
        } submit {
            displaysInvalidAuthError()
        }
    }

    @Test
    fun successfulLoginShouldOpenMainScreen() {
        loginScreen {
            typeUsername("user")
            typePassword("pass")
        } submit {
            opensMainScreen()
        }
    }
}