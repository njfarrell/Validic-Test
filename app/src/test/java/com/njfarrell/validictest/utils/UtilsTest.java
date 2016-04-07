package com.njfarrell.validictest.utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testGetPercentage() {
        int zeroPercent = 0;
        int fiftyPercent = 50;
        int oneHundredPrecent = 100;

        assertEquals(Utils.getPercentage(1, 1), oneHundredPrecent);
        assertEquals(Utils.getPercentage(5, 10), fiftyPercent);
        assertEquals(Utils.getPercentage(0, 1), zeroPercent);
        assertEquals(Utils.getPercentage(1, 0), zeroPercent);
    }

    @Test
    public void testReadInputStream() throws IOException {
        String fakeInput = "test";
        InputStream fakeStream = new ByteArrayInputStream(fakeInput.getBytes());
        assertEquals(Utils.readInputStream(fakeStream), fakeInput + "\n");
    }
}
