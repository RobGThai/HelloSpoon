package com.robgthai.spoon.hellospoon.google;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

import com.robgthai.spoon.hellospoon.R;
import com.squareup.spoon.Spoon;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Demonstrates the usage of
 * {@link android.support.test.espresso.action.ViewActions#scrollTo()}.
 */
@LargeTest
public class ScrollToTest extends ActivityInstrumentationTestCase2<ScrollActivity> {

    public ScrollToTest() {
        super(ScrollActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Espresso will not launch our activity for us, we must launch it via getActivity().
        getActivity();
    }

    // You can pass more than one action to perform. This is useful if you are performing two actions
    // back-to-back on the same view.
    // Note - scrollTo is a no-op if the view is already displayed on the screen.
    public void testScrollToInScrollView() {
        Spoon.screenshot(getActivity(), "Default");

        onView(withId(is(R.id.bottom_left)))
                .perform(scrollTo(), click());
        Spoon.screenshot(getActivity(), "Scrolled_to_bottom_left_and_clicked");
    }
}
