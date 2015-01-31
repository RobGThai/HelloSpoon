package com.robgthai.spoon.hellospoon;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;

import com.squareup.spoon.Spoon;

import junit.framework.Assert;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class LandingActivityTest extends ActivityInstrumentationTestCase2<LandingActivity>{

    private Activity mActivity;

    public LandingActivityTest(Class<LandingActivity> activityClass) {
        super(activityClass);
    }

    public LandingActivityTest() {
        this(LandingActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    public void test_txtHelloVisible() {
        Spoon.screenshot(mActivity, "Hello_World");
        View txtHello = mActivity.findViewById(R.id.txtHello);
        ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), txtHello);
    }

    public void test_txtHelloVisibleWithEspresso() {
        Spoon.screenshot(mActivity, "Hello_World_espresso");
        onView(withId(R.id.txtHello)).check(matches(isDisplayed()));
    }

    public void test_txtHelloSaysHello() {
        Spoon.screenshot(mActivity, "Hello_World_isHello");
        onView(withId(R.id.txtHello)).check(matches(withText(R.string.hello_world)));
    }

    public void test_txtHelloSaysHelloWithString() {
        Spoon.screenshot(mActivity, "Hello_World_isHello");
        onView(withId(R.id.txtHello)).check(matches(withText("Hello world!")));
    }

    public void test_click_txtHello_shouldOpen_EchoActivity() {
        Spoon.screenshot(mActivity, "Hello_World_before_click");
        onView(withId(R.id.txtHello)).perform(click());
        View v = mActivity.findViewById(R.id.txtHello);
        Spoon.screenshot(mActivity, "Hello_World_after_click");
        Assert.assertNull("txtHello should no longer exists", v);
    }

}