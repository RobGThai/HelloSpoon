package com.robgthai.spoon.hellospoon;

import com.robgthai.spoon.hellospoon.echo.Echoer;

import junit.framework.Assert;

import org.junit.Test;

public class EchoerTest {

    Echoer echoer;

    @Test
    public void test_should_echo_helloworld() {
        echoer = new Echoer("Hello World");
        Assert.assertEquals("Hello World", echoer.echo());
    }

    @Test
    public void test_should_echo_Hi() {
        echoer = new Echoer("Hi");
        Assert.assertEquals("Hi", echoer.echo());
    }
}