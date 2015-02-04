package com.robgthai.spoon.hellospoon.burst;

import com.robgthai.spoon.hellospoon.echo.EchoCollection;
import com.squareup.burst.BurstJUnit4;
import com.squareup.burst.annotation.Burst;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(BurstJUnit4.class)
public class BurstConstructorTest {
    @Burst EchoCollection echos;

    public BurstConstructorTest() {

    }

    @Test
    public void testConstructorIsWorking() {
        assertNotNull(echos);
    }

    @Test
    public void testMethodIsWorking(EchoCollection echos) {
        assertNotNull(echos);
    }
}
