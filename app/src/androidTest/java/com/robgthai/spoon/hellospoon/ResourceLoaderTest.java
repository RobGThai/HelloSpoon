package com.robgthai.spoon.hellospoon;

import android.os.Bundle;
import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

public class ResourceLoaderTest extends AndroidTestCase{

    @Test
    public void test_shouldGetBundle() {
        ResourceLoader rl = new ResourceLoader(getContext());
        Assert.assertNotNull(rl.getBundle());
    }

    @Test
    public void test_bundleShouldContainAppName() {
        ResourceLoader rl = new ResourceLoader(getContext());
        Bundle b = rl.getBundle();
        Assert.assertEquals("HelloSpoon", b.getString("app_name"));
    }
}