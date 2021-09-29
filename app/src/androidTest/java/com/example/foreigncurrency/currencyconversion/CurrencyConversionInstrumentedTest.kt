package com.example.foreigncurrency.currencyconversion

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.foreigncurrency.MainActivity
import com.example.foreigncurrency.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CurrencyConversionInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun convertCurrencyTest() {
        //GIVEN
        val selectedCurrency = "EUR"

        //WHEN
        onView(withText(selectedCurrency)).perform(ViewActions.click())
        onView(withId(R.id.et_amount)).perform(ViewActions.typeText("3.5"))

        //THEN
        onView(
            allOf(
                withParent(withChild(withText("PER"))),
                withParentIndex(1)
            )
        ).check(matches(withText("14.00")))
    }

}