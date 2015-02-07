package com.robgthai.spoon.hellospoon.google;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import com.robgthai.spoon.hellospoon.R;
import com.squareup.spoon.Spoon;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Highlights basic
 * {@link android.support.test.espresso.Espresso#onView(org.hamcrest.Matcher)}
 * functionality.
 */
@LargeTest
public class BasicTest extends ActivityInstrumentationTestCase2<SimpleActivity> {

    public BasicTest() {
        // This constructor was deprecated - but we want to support lower API levels.
        super(SimpleActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Espresso will not launch our activity for us, we must launch it via getActivity().
        getActivity();
    }

    public void testSimpleClickAndCheckText() {
        Spoon.screenshot(getActivity(), "Default");

        onView(withId(R.id.button_simple))
                .perform(click());
        Spoon.screenshot(getActivity(), "Simple_clicked");

        onView(withId(R.id.text_simple))
                .check(matches(withText("Hello Espresso!")));
        Spoon.screenshot(getActivity(), "Check_simple");
    }

    public void testTypingAndPressBack() {
        Spoon.screenshot(getActivity(), "Default");

        // Close soft keyboard after type to avoid issues on devices with soft keyboard.
        onView(withId(R.id.sendtext_simple))
                .perform(typeText("Have a cup of Espresso."), closeSoftKeyboard());
        Spoon.screenshot(getActivity(), "Closed_keyboard");

        onView(withId(R.id.send_simple))
                .perform(click());
        Spoon.screenshot(getActivity(), "Simple_clicked");

        // Clicking launches a new activity that shows the text entered above. You don't need to do
        // anything special to handle the activity transitions. Espresso takes care of waiting for the
        // new activity to be resumed and its view hierarchy to be laid out.
        onView(withId(R.id.display_data))
                .check(matches(withText(("Have a cup of Espresso."))));
        Spoon.screenshot(getActivity(), "Data_displayed");

        // Going back to the previous activity - lets make sure our text was perserved.
        pressBack();
        Spoon.screenshot(getActivity(), "Back_pressed");

        onView(withId(R.id.sendtext_simple))
                .check(matches(withText(containsString("Espresso"))));
        Spoon.screenshot(getActivity(), "Simple_clicked");
    }

    @SuppressWarnings("unchecked")
    public void testClickOnSpinnerItemAmericano() {
        Spoon.screenshot(getActivity(), "Default");
        // Open the spinner.
        onView(withId(R.id.spinner_simple))
                .perform(click());
        Spoon.screenshot(getActivity(), "Simple_clicked");

        // Spinner creates a List View with its contents - this can be very long and the element not
        // contributed to the ViewHierarchy - by using onData we force our desired element into the
        // view hierarchy.
        onData(allOf(is(instanceOf(String.class)), is("Americano")))
                .perform(click());
        Spoon.screenshot(getActivity(), "Americano_clicked");

        onView(withId(R.id.spinnertext_simple))
                .check(matches(withText(containsString("Americano"))));
        Spoon.screenshot(getActivity(), "Spinner_is_Americano");
    }
}

