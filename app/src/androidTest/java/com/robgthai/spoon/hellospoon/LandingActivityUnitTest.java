package com.robgthai.spoon.hellospoon;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.test.ActivityUnitTestCase;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.test.mock.MockApplication;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.TextView;

import com.squareup.spoon.Spoon;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LandingActivityUnitTest extends ActivityUnitTestCase<LandingActivity> {

    private LandingActivity mActivity;
    private FragmentManager mFragmentManager;

    private MockApplication mockApplication = new MockApplication() {
        @Override
        public void onCreate() {
            super.onCreate();
            setTheme(R.style.AppTheme);
        }
    };

    public LandingActivityUnitTest() {
        this(LandingActivity.class);
    }

    public LandingActivityUnitTest(Class<LandingActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    private void startActivity() {
//        setApplication(mockApplication);
        Context context = new ContextThemeWrapper(getInstrumentation().getTargetContext()
                , R.style.AppTheme);
        setActivityContext(context);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        mActivity = startActivity(intent, null, null);
    }

    private void startFragment(Fragment fragment, String tag) {
        mFragmentManager = mActivity.getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(fragment, tag).commit();
        mFragmentManager.executePendingTransactions();
    }

    @UiThreadTest
    public void test_Preconditions() {
        startActivity();
        assertNotNull("Activity is null", getActivity());
    }

    @UiThreadTest
    public void test_fragment_exists() {
        startActivity();
        startFragment(LandingFragment.newInstance(), "fragment");

        LandingFragment lf = (LandingFragment) mFragmentManager.findFragmentByTag("fragment");
        Spoon.screenshot(mActivity, "LandingFragment");

        assertNotNull("Fragment is null", lf);
//        assertNotNull("Fragment View is null", lf.getView());
    }

//    @UiThreadTest
//    public void test_txtHello_exists() {
//        startActivity();
//        startFragment(LandingFragment.newInstance(), "fragment");
//
//        LandingFragment lf = (LandingFragment) mFragmentManager.findFragmentByTag("fragment");
//        Spoon.screenshot(mActivity, "Hello_World");
//
//        View txtHello = lf.getView().findViewById(R.id.txtHello);
//        assertNotNull("txtHello is null", txtHello);
//        assertEquals("txtHello is not TextView", TextView.class.getName(), txtHello.getClass().getName());
////        ViewAsserts.assertOnScreen(mActivity.getWindow().getDecorView(), txtHello);
//    }

//    public void test_txtHelloVisibleWithEspresso() {
//        startActivity();
//
//        Spoon.screenshot(mActivity, "Hello_World_espresso");
//        onView(withId(R.id.txtHello)).check(matches(isDisplayed()));
//    }
//
//    public void test_txtHelloSaysHello() {
//        startActivity();
//
//        Spoon.screenshot(mActivity, "Hello_World_isHello");
//        onView(withId(R.id.txtHello)).check(matches(withText(R.string.hello_world)));
//    }
}