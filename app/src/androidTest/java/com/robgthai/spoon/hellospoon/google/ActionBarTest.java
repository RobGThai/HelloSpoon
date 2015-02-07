package com.robgthai.spoon.hellospoon.google;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.robgthai.spoon.hellospoon.R;
import com.squareup.spoon.Spoon;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Demonstrates Espresso with action bar and contextual action mode.
 * mode.
 */
@LargeTest
public class ActionBarTest extends ActivityInstrumentationTestCase2<ActionBarTestActivity> {

    private ActionBarTestActivity mActivity;

    public ActionBarTest() {
        // This constructor was deprecated - but we want to support lower API levels.
        super(ActionBarTestActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Espresso will not launch our activity for us, we must launch it via getActivity().
        mActivity = getActivity();
    }

    @SuppressWarnings("unchecked")
    public void testClickActionBarItem() {
        Spoon.screenshot(mActivity, "Default_state");

        onView(withId(R.id.hide_contextual_action_bar))
                .perform(click());
        Spoon.screenshot(mActivity, "Hide_ab_click");

        onView(withId(R.id.action_save))
                .perform(click());
        Spoon.screenshot(mActivity, "Save_click");

        onView(withId(R.id.text_action_bar_result))
                .check(matches(withText("Save")));
        Spoon.screenshot(mActivity, "After_save");
    }

    @SuppressWarnings("unchecked")
    public void testClickActionModeItem() {
        Spoon.screenshot(mActivity, "Default_state");

        onView(withId(R.id.show_contextual_action_bar))
                .perform(click());
        Spoon.screenshot(mActivity, "Show_ab_click");

        onView((withId(R.id.action_lock)))
                .perform(click());
        Spoon.screenshot(mActivity, "Lock_click");

        onView(withId(R.id.text_action_bar_result))
                .check(matches(withText("Lock")));
        Spoon.screenshot(mActivity, "After_lock");
    }


    @SuppressWarnings("unchecked")
    public void testActionBarOverflow() {
        Spoon.screenshot(mActivity, "Default_state");

        onView(withId(R.id.hide_contextual_action_bar))
                .perform(click());
        Spoon.screenshot(mActivity, "Hide_ab_click");

        // Open the overflow menu from action bar
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        Spoon.screenshot(mActivity, "Open_overflow");

        onView(withText("World"))
                .perform(click());
        Spoon.screenshot(mActivity, "World_clicked");

        onView(withId(R.id.text_action_bar_result))
                .check(matches(withText("World")));
        Spoon.screenshot(mActivity, "After_world");
    }

    @SuppressWarnings("unchecked")
    public void testActionModeOverflow() {
        Spoon.screenshot(mActivity, "Default_state");

        onView(withId(R.id.show_contextual_action_bar))
                .perform(click());
        Spoon.screenshot(mActivity, "Show_ab_click");

        // Open the overflow menu from contextual action mode.
        openContextualActionModeOverflowMenu();
        Spoon.screenshot(mActivity, "Open_overflow");

        onView(withText("Key"))
                .perform(click());
        Spoon.screenshot(mActivity, "Key_clicked");

        onView(withId(R.id.text_action_bar_result))
                .check(matches(withText("Key")));
        Spoon.screenshot(mActivity, "After_key");
    }
}
