package nl.jovmit.login

import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.typeText
import nl.jovmit.*

@DslMarker
private annotation class TestRobotMarker

@TestRobotMarker
private interface LoginRobot

class LoginTestRobot : LoginRobot {

    companion object {

        fun loginScreen(block: LoginTestRobot.() -> Unit): LoginTestRobot {
            return LoginTestRobot().apply(block)
        }
    }

    fun hasLoginTitle() = toolbarWithTitle(R.string.login) check isDisplayed

    fun includesUsername() = R.id.loginUsername check isDisplayed

    fun includesPassword() = R.id.loginPassword check isDisplayed

    fun includesLoginButton() = R.id.loginButton check isDisplayed

    fun typeUsername(username: String) = R.id.loginUsername perform typeText(username)

    fun typePassword(password: String) = R.id.loginPassword perform typeText(password)

    fun usernameEditor(block: UsernameRobot.() -> Unit): UsernameRobot {
        return UsernameRobot().apply(block)
    }

    fun passwordEditor(block: PasswordRobot.() -> Unit): PasswordRobot {
        return PasswordRobot().apply(block)
    }

    fun loginButton(block: LoginButtonRobot.() -> Unit): LoginButtonRobot {
        return LoginButtonRobot().apply(block)
    }

    infix fun submit(block: LoginResult.() -> Unit): LoginResult {
        R.id.loginButton perform ViewActions.click()
        return LoginResult().apply(block)
    }
}

class UsernameRobot : LoginRobot {

    fun hasCorrectHint() = R.id.loginUsername hasHint R.string.username
}

class PasswordRobot : LoginRobot {

    fun hasCorrectHint() = R.id.loginPassword hasHint R.string.password
}

class LoginButtonRobot : LoginRobot {

    fun hasCorrectTitle() = R.id.loginButton hasText R.string.login
}

class LoginResult : LoginRobot {

    fun displaysEmptyUsernameError() = text(R.string.errorEmptyUsername) check isDisplayed

    fun displaysEmptyPasswordError() = text(R.string.errorEmptyPassword) check isDisplayed

    fun displaysInvalidAuthError() = text(R.string.errorInvalidLogin) check isDisplayed

    fun opensMainScreen() = R.id.mainGreetingMessage check isDisplayed
}