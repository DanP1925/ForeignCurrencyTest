package com.example.foreigncurrency.supportedcountries

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.foreigncurrency.MainActivity
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class SupportedCountriesInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun injectHilt() {
        hiltRule.inject()
    }

    @Test
    fun openCurrencyConversionTest() {
        //WHEN
        onView(withText("EUR")).perform(click())

        //THEN
        activityRule.scenario.onActivity { activity ->
            assertThat(activity.title.contains("EUR")).isEqualTo(true)
        }
    }
}