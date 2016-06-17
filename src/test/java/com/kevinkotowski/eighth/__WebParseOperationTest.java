package com.kevinkotowski.eighth;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevinkotowski on 6/16/16.
 */
public class __WebParseOperationTest {
    @Test
    public void parseOperationString() throws Exception {
        String[] operation = WebParseOperation.parse("method=value");
        assertEquals("method", operation[0]);
        assertEquals("value", operation[1]);
    }

}
