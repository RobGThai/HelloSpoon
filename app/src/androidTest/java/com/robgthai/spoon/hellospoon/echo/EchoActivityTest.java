package com.robgthai.spoon.hellospoon.echo;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestRunner;

import com.robgthai.spoon.hellospoon.common.BurstInstrumentationTestRunner;
import com.squareup.burst.BurstAndroid;
import com.squareup.spoon.Spoon;

import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class EchoActivityTest extends ActivityInstrumentationTestCase2<EchoActivity> {
    private final EchoCollection echos;

    private EchoActivity mActivity;

    public EchoActivityTest() {
        this(EchoActivity.class, null);
    }

//    public EchoActivityTest(Class<EchoActivity> activityClass) {
//        this(activityClass, null);
//    }

    public EchoActivityTest(Class<EchoActivity> activityClass, EchoCollection echos) {
        super(activityClass);
        this.echos = echos;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Intent i = new Intent();
        i.putExtra(EchoActivity.MESSAGE, "OMG");
        setActivityIntent(i);
        mActivity = getActivity();
    }

    public void testEchoOMG() {
        Spoon.screenshot(mActivity, "Echo_Activity");
        onView(withText("OMG")).check(matches(isDisplayed()));
    }

//    public void testEchoBurst() {
//        Spoon.screenshot(mActivity, "Echo_Activity_" + echos.name());
//        onView(withText(echos.name())).check(matches(isDisplayed()));
//    }

}