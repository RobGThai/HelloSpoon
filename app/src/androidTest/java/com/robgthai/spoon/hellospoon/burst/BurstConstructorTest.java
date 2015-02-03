package com.robgthai.spoon.hellospoon.burst;

import com.robgthai.spoon.hellospoon.echo.EchoCollection;
import com.squareup.burst.annotation.Burst;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class BurstConstructorTest {
    @Burst EchoCollection echos;

    public BurstConstructorTest() {

    }

    @Test
    public void testConstructorIsWorking() {
        assertNotNull(echos);
    }
}
