package org.fossasia.susi.ai.login

import android.Manifest
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.view.WindowManager
import java.io.IOException
import org.fossasia.susi.ai.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import timber.log.Timber

/**
 * Created by collinx on 22-10-2017.
 */

@RunWith(AndroidJUnit4::class)
@MediumTest
class LoginActivityTest {

    @Rule
    @JvmField
    val permissionRule: TestRule = GrantPermissionRule.grant(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(LoginActivity::class.java)

    @Before
    @Throws(IOException::class, InterruptedException::class)
    fun unlockScreen() {
        Timber.d("running unlockScreen..")

        val activity = mActivityRule.activity
        val wakeUpDevice = Runnable {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
        activity.runOnUiThread(wakeUpDevice)
    }

    @Test
    fun testUIViewsPresenceOnLoad() {
        Timber.d("running testUIViewsPresenceOnLoad..")

        // checks if email input field is present
        onView(withId(R.id.email)).check(matches(isDisplayed()))

        // checks if password input field is present
        onView(withId(R.id.password)).check(matches(isDisplayed()))

        // checks if login button is present
        onView(withId(R.id.logIn)).check(matches(isDisplayed()))

        // checks if forgot password button is present
        onView(withId(R.id.forgotPassword)).check(matches(isDisplayed()))

        // checks if checkbox is present
        onView(withId(R.id.customer_server)).check(matches(isDisplayed()))

        // checks if sign up button is present
        onView(withId(R.id.signUp)).perform(scrollTo())
        onView(withId(R.id.signUp)).check(matches(isDisplayed()))

        // checks if skip button is present
        onView(withId(R.id.skip)).perform(scrollTo())
        onView(withId(R.id.skip)).check(matches(isDisplayed()))
    }
}
