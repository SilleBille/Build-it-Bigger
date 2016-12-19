package com.mkd.build.it.bigger;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.mkd.build.it.bigger.jokeviewer.JokeViewerActivity;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTest {
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);


    @Test
    public void sayHello() {
        onView(withId(R.id.button2)).perform(click());
        // Tutorial from https://google.github.io/android-testing-support-library/docs/espresso/intents/
        intended(hasComponent(hasClassName(JokeViewerActivity.class.getName())));
        // All possible errors
        intended(allOf(
                hasExtra(equalTo(Intent.EXTRA_TEXT), notNullValue()),
                hasExtra(equalTo(Intent.EXTRA_TEXT), not("connect timed out")),
                hasExtra(equalTo(Intent.EXTRA_TEXT), not("connection refused"))
        ));
    }

}
