package com.robgthai.spoon.hellospoon;

import junit.framework.Assert;

import org.junit.Test;

public class EchoerTest {

    Echoer echoer;

    @Test
    public void should_echo_helloworld() {
        echoer = new Echoer("Hello World");
        Assert.assertEquals("Hello World", echoer.echo());
    }

    @Test
    public void should_echo_Hi() {
        echoer = new Echoer("Hi");
        Assert.assertEquals("Hi", echoer.echo());
    }
}