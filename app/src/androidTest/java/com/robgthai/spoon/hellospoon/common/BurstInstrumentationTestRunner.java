package com.robgthai.spoon.hellospoon.common;

import android.test.AndroidTestRunner;
import android.test.InstrumentationTestRunner;

import com.squareup.burst.BurstAndroid;

public class BurstInstrumentationTestRunner extends InstrumentationTestRunner {
    private final BurstAndroid testRunner = new BurstAndroid();

    @Override
    protected AndroidTestRunner getAndroidTestRunner() {
        return testRunner;
    }
}
