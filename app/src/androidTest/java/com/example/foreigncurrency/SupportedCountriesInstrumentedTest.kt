package com.example.foreigncurrency

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SupportedCountriesInstrumentedTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun openCurrencyConversionTest() {
        //GIVEN
        val currency = "EUR"

        //WHEN
        onView(withText("EUR")).perform(click())

        //THEN
        activityRule.scenario.onActivity { activity ->
            assertThat(activity.title.contains("EUR")).isEqualTo(true)
        }
    }
}