package com.robgthai.spoon.hellospoon.google;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressMenuKey;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.robgthai.spoon.hellospoon.R;
import com.squareup.spoon.Spoon;

import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Ensures view root ordering works properly.
 */
@LargeTest
public class MenuTest extends ActivityInstrumentationTestCase2<MenuActivity> {
    public MenuTest() {
        super(MenuActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testPopupMenu() {
        Spoon.screenshot(getActivity(), "Default");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            // popup menus are post honeycomb.
            return;
        }
        onView(withText(R.string.popup_item_1_text)).check(doesNotExist());
        Spoon.screenshot(getActivity(), "Check_popup_doesnt_exists");

        onView(withId(R.id.popup_button)).perform(click());
        Spoon.screenshot(getActivity(), "Popup_clicked");

        onView(withText(R.string.popup_item_1_text)).check(matches(isDisplayed())).perform(click());
        Spoon.screenshot(getActivity(), "Popup_item1_clicked");

        onView(withId(R.id.text_menu_result)).check(matches(withText(R.string.popup_item_1_text)));
        Spoon.screenshot(getActivity(), "Check_popup_item1");
    }

    public void testContextMenu() {
        Spoon.screenshot(getActivity(), "Default");

        onView(withText(R.string.context_item_2_text)).check(doesNotExist());
        Spoon.screenshot(getActivity(), "Check_context_item2_doesnt_exists");

        onView(withId(R.id.text_context_menu)).perform(longClick());
        Spoon.screenshot(getActivity(), "Long_clicked_context_menu");

        onView(withText(R.string.context_item_2_text)).check(matches(isDisplayed())).perform(click());
        Spoon.screenshot(getActivity(), "Clicked_context_item2_if_displayed");

        onView(withId(R.id.text_menu_result)).check(matches(withText(R.string.context_item_2_text)));
        Spoon.screenshot(getActivity(), "Check_if_context_item2_displayed");
    }

    public void testOptionMenu() {
        Spoon.screenshot(getActivity(), "Default");

        onView(withText(R.string.options_item_3_text)).check(doesNotExist());
        Spoon.screenshot(getActivity(), "Check_if_item3_does_not_exist");

        onView(isRoot()).perform(pressMenuKey());
        Spoon.screenshot(getActivity(), "Clicked_menu");

        onView(withText(R.string.options_item_3_text)).check(matches(isDisplayed())).perform(click());
        Spoon.screenshot(getActivity(), "Clicked_option_3_if_displayed");

        onView(withId(R.id.text_menu_result)).check(matches(withText(R.string.options_item_3_text)));
        Spoon.screenshot(getActivity(), "Check_result_display_option3");
    }
}
