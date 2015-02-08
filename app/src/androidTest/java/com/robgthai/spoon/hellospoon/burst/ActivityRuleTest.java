package com.robgthai.spoon.hellospoon.burst;

import android.app.Activity;
import android.os.Bundle;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import com.robgthai.spoon.hellospoon.LandingActivity;
import com.robgthai.spoon.hellospoon.R;
import com.robgthai.spoon.hellospoon.common.ActivityRule;
import com.robgthai.spoon.hellospoon.echo.EchoActivity;
import com.robgthai.spoon.hellospoon.echo.EchoCollection;
import com.squareup.burst.BurstJUnit4;
import com.squareup.burst.annotation.Burst;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;

@SmallTest
@RunWith(BurstJUnit4.class)
public class ActivityRuleTest {
    @Rule public ActivityRule<LandingActivity> echoActivityRule = new ActivityRule<>(LandingActivity.class);

//    @Burst
//    EchoCollection echos;

    @Before
    public void setup() {

    }

    @Test
    public void testLandingActivityExists() {
        assertNotNull("LandingActivity is null", echoActivityRule.get());
    }

//    @Test
//    public void test_BurstWithActivity() {
//        Bundle bundle = new Bundle();
//        bundle.putString(EchoActivity.MESSAGE, echos.name());
//        echoActivityRule.putExtras(bundle);
//        echoActivityRule.instrumentation();
//        Activity act = echoActivityRule.get();
//
////        TextView echo = (TextView) act.findViewById(R.id.txtEcho);
//
//        onView(withText(echos.name()))
//                .inRoot(withDecorView(is(act.getWindow().getDecorView())))
//                .check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void test_txtHelloVisibleWithEspresso() {
//        Spoon.screenshot(echoActivityR.get(), "Hello_World_espresso");
//        onView(withId(R.id.txtHello)).check(matches(isDisplayed()));
//    }
}
