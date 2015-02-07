package com.robgthai.spoon.hellospoon.google;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.robgthai.spoon.hellospoon.R;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Demonstrates Espresso with action bar and contextual action mode.
 * mode.
 */
@LargeTest
public class ActionBarTest extends ActivityInstrumentationTestCase2<ActionBarTestActivity> {
  public ActionBarTest() {
    // This constructor was deprecated - but we want to support lower API levels.
    super(ActionBarTestActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    // Espresso will not launch our activity for us, we must launch it via getActivity().
    getActivity();
  }

  @SuppressWarnings("unchecked")
  public void testClickActionBarItem() {
    onView(withId(R.id.hide_contextual_action_bar))
      .perform(click());

    onView(withId(R.id.action_save))
      .perform(click());

    onView(withId(R.id.text_action_bar_result))
      .check(matches(withText("Save")));
  }

  @SuppressWarnings("unchecked")
  public void testClickActionModeItem() {
    onView(withId(R.id.show_contextual_action_bar))
      .perform(click());

    onView((withId(R.id.action_lock)))
      .perform(click());

    onView(withId(R.id.text_action_bar_result))
      .check(matches(withText("Lock")));
  }


  @SuppressWarnings("unchecked")
  public void testActionBarOverflow() {
    onView(withId(R.id.hide_contextual_action_bar))
      .perform(click());

    // Open the overflow menu from action bar
    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(withText("World"))
      .perform(click());

    onView(withId(R.id.text_action_bar_result))
      .check(matches(withText("World")));
  }

  @SuppressWarnings("unchecked")
  public void testActionModeOverflow() {
    onView(withId(R.id.show_contextual_action_bar))
      .perform(click());

    // Open the overflow menu from contextual action mode.
    openContextualActionModeOverflowMenu();

    onView(withText("Key"))
      .perform(click());

    onView(withId(R.id.text_action_bar_result))
      .check(matches(withText("Key")));
  }
}
