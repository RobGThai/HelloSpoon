package com.robgthai.spoon.hellospoon.echo;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.squareup.burst.annotation.Burst;
import com.squareup.spoon.Spoon;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class EchoActivityTest extends ActivityInstrumentationTestCase2<EchoActivity> {
    @Burst
    public EchoCollection echos;

    private EchoActivity mActivity;

    public EchoActivityTest() {
        super(EchoActivity.class);
    }

//    public EchoActivityTest(Class<EchoActivity> activityClass) {
//        this(activityClass, null);
//    }

//    public EchoActivityTest(Class<EchoActivity> activityClass, EchoCollection echos) {
//        super(activityClass);
//        this.echos = echos;
//    }

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