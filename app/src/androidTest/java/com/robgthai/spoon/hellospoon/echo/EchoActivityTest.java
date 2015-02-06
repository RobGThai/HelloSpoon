package com.robgthai.spoon.hellospoon.echo;

import android.content.Intent;
import android.support.test.espresso.FailureHandler;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;

import com.robgthai.spoon.hellospoon.R;
import com.squareup.burst.annotation.Burst;
import com.squareup.spoon.Spoon;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class EchoActivityTest extends ActivityInstrumentationTestCase2<EchoActivity> {
//    @Burst
//    public EchoCollection echos;

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
    }

    private void openActivityWithBundle(String message) {
        Intent i = new Intent();
        i.putExtra(EchoActivity.MESSAGE, message);
        setActivityIntent(i);
        mActivity = getActivity();
    }

    public void testEchoOMG() {
        openActivityWithBundle("OMG");
        Spoon.screenshot(mActivity, "Echo_Activity");
        onView(withText("OMG")).check(matches(isDisplayed()));
    }

    public void testEchoCool() {
        openActivityWithBundle("Cool");
        Spoon.screenshot(mActivity, "Echo_Activity");
        onView(withText("Cool")).check(matches(isDisplayed()));
    }

    public void testEchoActivityContainImage() {
        openActivityWithBundle("OMG");
        Spoon.screenshot(mActivity, "Echo_Activity");
        ImageView photo = (ImageView) mActivity.findViewById(R.id.imgPhoto);

        assertNotNull("View R.id.imgPhoto not found", photo);
//        onView(allOf(withId(R.id.imgPhoto), withContentDescription("OMG"))).withFailureHandler(new FailureHandler() {
//            @Override
//            public void handle(Throwable throwable, Matcher<View> viewMatcher) {
//                assertEquals("LOL", throwable.getClass());
//            }
//        }).check(matches(isDisplayed()));
    }

    public void testEchoActivityContainImageDisplayIcon() {
        openActivityWithBundle("OMG");
        Spoon.screenshot(mActivity, "Echo_Activity");
        onView(allOf(withId(R.id.imgPhoto)
                , withContentDescription("OMG"))).check(matches(isDisplayed()));
    }

    public void testEchoActivityClickImageDisplayToast() {
        openActivityWithBundle("OMG");
        Spoon.screenshot(mActivity, "Echo_Activity");
        onView(withId(R.id.imgPhoto)).perform(click());
        onView(withText("Hello"))
                .inRoot(withDecorView(is(not(getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        Spoon.screenshot(mActivity, "Image_clicked");

    }

//    public void testEchoBurst() {
//        Spoon.screenshot(mActivity, "Echo_Activity_" + echos.name());
//        onView(withText(echos.name())).check(matches(isDisplayed()));
//    }

//    public void testFailureHandler() {
//        openActivityWithBundle("OMG");
//        Spoon.screenshot(mActivity, "Echo_Activity");
//        ImageView photo = (ImageView) mActivity.findViewById(R.id.imgPhoto);
//
//        assertNotNull("View R.id.imgPhoto not found", photo);
////        onView(allOf(withId(R.id.imgPhoto), withContentDescription("OMG"))).withFailureHandler(new FailureHandler() {
////            @Override
////            public void handle(Throwable throwable, Matcher<View> viewMatcher) {
////                assertEquals("LOL", throwable.getClass());
////            }
////        }).check(matches(isDisplayed()));
//    }

}