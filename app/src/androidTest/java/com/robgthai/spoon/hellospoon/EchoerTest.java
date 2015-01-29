package com.robgthai.spoon.hellospoon;

import junit.framework.Assert;

import org.junit.Test;

public class EchoerTest {

    Echoer echoer;
    @Test
    public void test_echoMessage() {
        echoer = new Echoer("Hello World");
        Assert.assertEquals("Hello World", echoer.echo());
    }
}