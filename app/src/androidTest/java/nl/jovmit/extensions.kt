package nl.jovmit

import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withParent
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v7.widget.Toolbar
import org.hamcrest.Matchers.allOf

val isDisplayed: ViewAssertion = matches(ViewMatchers.isDisplayed())

fun toolbarWithTitle(@StringRes title: Int): ViewInteraction =
        onView(allOf(withText(title), withParent(isAssignableFrom(Toolbar::class.java))))

fun text(@StringRes resource: Int): ViewInteraction = onView(withText(resource))

infix fun ViewInteraction.check(action: ViewAssertion): ViewInteraction = this.check(action)

infix fun ViewInteraction.hasHint(@StringRes string: Int): ViewInteraction = this check matches(withHint(string))

infix fun ViewInteraction.hasText(@StringRes string: Int): ViewInteraction = this check matches(withText(string))

infix fun Int.perform(action: ViewAction): ViewInteraction = onView(withId(this)).perform(action)

infix fun Int.check(action: ViewAssertion): ViewInteraction = onView(withId(this)).check(action)

infix fun Int.hasHint(@StringRes resource: Int): ViewInteraction = onView(withId(this)) hasHint resource

infix fun Int.hasText(@StringRes resource: Int): ViewInteraction = onView(withId(this)) hasText resource