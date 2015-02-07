package com.robgthai.spoon.hellospoon.google;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.base.Preconditions.checkNotNull;

import android.support.test.espresso.contrib.CountingIdlingResource;

import com.robgthai.spoon.hellospoon.R;
import com.robgthai.spoon.hellospoon.google.SyncActivity.HelloWorldServer;
import com.squareup.spoon.Spoon;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * Example for {@link CountingIdlingResource}. Demonstrates how to wait on a delayed response from
 * request before continuing with a test.
 */
@LargeTest
public class AdvancedSynchronizationTest extends ActivityInstrumentationTestCase2<SyncActivity> {

    private class DecoratedHelloWorldServer implements HelloWorldServer {
        private final HelloWorldServer realHelloWorldServer;
        private final CountingIdlingResource helloWorldServerIdlingResource;

        private DecoratedHelloWorldServer(HelloWorldServer realHelloWorldServer,
                                          CountingIdlingResource helloWorldServerIdlingResource) {
            this.realHelloWorldServer = checkNotNull(realHelloWorldServer);
            this.helloWorldServerIdlingResource = checkNotNull(helloWorldServerIdlingResource);
        }

        @Override
        public String getHelloWorld() {
            // Use CountingIdlingResource to track in-flight calls to getHelloWorld (a simulation of a
            // network call). Whenever the count goes to zero, Espresso will be notified that this
            // resource is idle and the test will be able to proceed.
            helloWorldServerIdlingResource.increment();
            try {
                return realHelloWorldServer.getHelloWorld();
            } finally {
                helloWorldServerIdlingResource.decrement();
            }
        }
    }

    public AdvancedSynchronizationTest() {
        super(SyncActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        SyncActivity activity = getActivity();
        HelloWorldServer realServer = activity.getHelloWorldServer();
        // Here, we use CountingIdlingResource - a common convenience class - to track the idle state of
        // the server. You could also do this yourself, by implementing the IdlingResource interface.
        CountingIdlingResource countingResource = new CountingIdlingResource("HelloWorldServerCalls");
        activity.setHelloWorldServer(new DecoratedHelloWorldServer(realServer, countingResource));
        registerIdlingResources(countingResource);
    }

    public void testCountingIdlingResource() {
        // Request the "hello world!" text by clicking on the request button.
        Spoon.screenshot(getActivity(), "Default_state");

        onView(withId(R.id.request_button)).perform(click());
        Spoon.screenshot(getActivity(), "Request_click");

        // Espresso waits for the resource to go idle and then continues.

        // The check if the text is visible can pass now.
        onView(withId(R.id.status_text)).check(matches(withText(R.string.hello_world)));
        Spoon.screenshot(getActivity(), "Check_if_status_is_match");
    }
}
