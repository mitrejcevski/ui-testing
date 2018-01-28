package nl.jovmit.login

import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import nl.jovmit.*
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
        toolbarWithTitle(R.string.login) check isDisplayed
    }

    @Test
    fun shouldContainUsernameInput() {
        R.id.loginUsername check isDisplayed
    }

    @Test
    fun usernameInputShouldApplyCorrectHint() {
        R.id.loginUsername hasHint R.string.username
    }

    @Test
    fun shouldContainPasswordInput() {
        R.id.loginPassword check isDisplayed
    }

    @Test
    fun passwordInputShouldApplyCorrectHint() {
        R.id.loginPassword hasHint R.string.password
    }

    @Test
    fun shouldContainLoginButton() {
        R.id.loginButton check isDisplayed
    }

    @Test
    fun loginButtonShouldApplyCorrectText() {
        R.id.loginButton hasText R.string.login
    }

    @Test
    fun loginWithoutUsernameShouldDisplayError() {
        R.id.loginButton perform click()
        text(R.string.errorEmptyUsername) check isDisplayed
    }

    @Test
    fun loginWithEmptyPasswordShouldDisplayError() {
        R.id.loginUsername perform typeText("username")
        R.id.loginButton perform click()
        text(R.string.errorEmptyPassword) check isDisplayed
    }

    @Test
    fun loginWithIncorrectCredentialsShouldDisplayError() {
        R.id.loginUsername perform typeText("username")
        R.id.loginPassword perform typeText("password")
        R.id.loginButton perform click()
        text(R.string.errorInvalidLogin) check isDisplayed
    }

    @Test
    fun successfulLoginShouldOpenMainScreen() {
        R.id.loginUsername perform typeText("user")
        R.id.loginPassword perform typeText("pass")
        R.id.loginButton perform click()
        R.id.mainGreetingMessage check isDisplayed
    }
}