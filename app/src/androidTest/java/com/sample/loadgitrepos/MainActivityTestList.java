package com.sample.loadgitrepos;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class MainActivityTestList {

    @Rule
   public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureListViewIsPresent() throws Exception {
        onView(withId(R.id.name)).check(matches(notNullValue() ));
        onView(withId(R.id.name)).check(matches(withText("React Hooks library for remote data fetching")));
        onView(withId(R.id.description)).check(matches(notNullValue() ));
        onView(withId(R.id.description)).check(matches(withText("description")));
        onView(withId(R.id.avatar)).check(matches(notNullValue() ));
        onView(withId(R.id.username)).check(matches(notNullValue()));
        onView(withId(R.id.username)).check(matches(withText("username")));
        onView(withId(R.id.rating_image)).check(matches(notNullValue()));
        onView(withId(R.id.stars)).check(matches(notNullValue()));
        onView(withId(R.id.stars)).check(matches(withText("5.0")));
        onView(withId(R.id.linear_recyclerview)).check(matches(hasDescendant(withText("React Hooks library for remote data fetching"))));


    }

}