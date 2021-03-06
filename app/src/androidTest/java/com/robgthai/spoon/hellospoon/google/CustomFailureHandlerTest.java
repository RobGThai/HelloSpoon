package com.robgthai.spoon.hellospoon.google;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.setFailureHandler;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.base.DefaultFailureHandler;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;

import com.squareup.spoon.Spoon;

import org.hamcrest.Matcher;

/**
 * A sample of how to set a non-default {@link FailureHandler}.
 */
@LargeTest
public class CustomFailureHandlerTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private static final String TAG = CustomFailureHandlerTest.class.getSimpleName();

    public CustomFailureHandlerTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
        setFailureHandler(new CustomFailureHandler(getInstrumentation().getTargetContext()));
    }

    public void testWithCustomFailureHandler() {
        Spoon.screenshot(getActivity(), "Default");

        try {
            onView(withText("does not exist")).perform(click());
        } catch (MySpecialException expected) {
            Log.e(TAG, "Special exception is special and expected: ", expected);
        }
    }

    /**
     * A {@link FailureHandler} that re-throws {@link NoMatchingViewException} as
     * {@link CustomFailureHandlerTest.MySpecialException}. All other functionality is delegated to
     * {@link DefaultFailureHandler}.
     */
    private static class CustomFailureHandler implements FailureHandler {
        private final FailureHandler delegate;

        public CustomFailureHandler(Context targetContext) {
            delegate = new DefaultFailureHandler(targetContext);
        }

        @Override
        public void handle(Throwable error, Matcher<View> viewMatcher) {
            try {
                delegate.handle(error, viewMatcher);
            } catch (NoMatchingViewException e) {
                throw new MySpecialException(e);
            }
        }
    }

    private static class MySpecialException extends RuntimeException {
        MySpecialException(Throwable cause) {
            super(cause);
        }
    }
}
