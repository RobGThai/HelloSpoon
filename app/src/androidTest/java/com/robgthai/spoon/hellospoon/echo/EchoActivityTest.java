package com.robgthai.spoon.hellospoon.echo;

import android.test.ActivityInstrumentationTestCase2;

public class EchoActivityTest extends ActivityInstrumentationTestCase2<EchoActivity> {

    private EchoActivity mActivity;

    public EchoActivityTest() {
        this(EchoActivity.class);
    }

    public EchoActivityTest(Class<EchoActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

}