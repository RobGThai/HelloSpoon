package com.robgthai.spoon.hellospoon.google;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.action.ViewActions;

import com.robgthai.spoon.hellospoon.R;
import com.squareup.spoon.Spoon;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Demonstrates use of {@link ViewActions#swipeLeft()} and {@link ViewActions#swipeRight()}.
 */
@LargeTest
public class SwipeTest extends ActivityInstrumentationTestCase2<ViewPagerActivity> {

    public SwipeTest() {
        super(ViewPagerActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testSwipingThroughViews() {
        Spoon.screenshot(getActivity(), "Default");
        // Should be on position 0 to start with.
        onView(withText("Position #0")).check(matches(isDisplayed()));
        Spoon.screenshot(getActivity(), "Checked_position_0_is_displayed");

        // Swipe left once.
        onView(withId(R.id.pager_layout)).perform(swipeLeft());
        Spoon.screenshot(getActivity(), "Swiped_left_on_pager");

        // Now position 1 should be visible.
        onView(withText("Position #1")).check(matches(isDisplayed()));
        Spoon.screenshot(getActivity(), "Checked_if_position_1_is_displayed");

        // Swipe left again.
        onView(withId(R.id.pager_layout)).perform(swipeLeft());
        Spoon.screenshot(getActivity(), "Swiped_left_on_pager");

        // Now position 2 should be visible.
        onView(withText("Position #2")).check(matches(isDisplayed()));
        Spoon.screenshot(getActivity(), "Checked_if_position_2_is_displayed");

        // Swipe left again.
        onView(withId(R.id.pager_layout)).perform(swipeLeft());
        Spoon.screenshot(getActivity(), "Swiped_left_on_pager");

        // Position 2 should still be visible as this is the last view in the pager.
        onView(withText("Position #2")).check(matches(isDisplayed()));
        Spoon.screenshot(getActivity(), "Checked_if_position_2_is_still_displayed");
    }

    public void testSwipingBackAndForward() {
        Spoon.screenshot(getActivity(), "Default");

        // Should be on position 0 to start with.
        onView(withText("Position #0")).check(matches(isDisplayed()));
        Spoon.screenshot(getActivity(), "Check_if_position_0_is_displayed");

        // Swipe left once.
        onView(withId(R.id.pager_layout)).perform(swipeLeft());
        Spoon.screenshot(getActivity(), "Swiped_left_on_pager");

        // Now position 1 should be visible.
        onView(withText("Position #1")).check(matches(isDisplayed()));
        Spoon.screenshot(getActivity(), "Check_if_position_1_is_displayed");

        // Swipe back to the right.
        onView(withId(R.id.pager_layout)).perform(swipeRight());
        Spoon.screenshot(getActivity(), "Swiped_right_on_pager");

        // Now position 0 should be visible again.
        onView(withText("Position #0")).check(matches(isDisplayed()));
        Spoon.screenshot(getActivity(), "Check_if_position_0_is_displayed");

        // Swipe right again.
        onView(withId(R.id.pager_layout)).perform(swipeRight());
        Spoon.screenshot(getActivity(), "Swiped_right_on_pager");

        // Position 0 should still be visible as this is the first view in the pager.
        onView(withText("Position #0")).check(matches(isDisplayed()));
        Spoon.screenshot(getActivity(), "Check_if_position_0_is_still_displayed");
    }

}
