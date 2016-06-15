package com.kevinkotowski.eighth;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class __WebScan {
    @Test
    public void scanContentGetValue() throws Exception {
        String content = "name=value\nkevin=awesome\nlast=value2";
        assertEquals("awesome", WebScan.get(content, "kevin"));
    }

    @Test
    public void scanContentGetValueIgnore2nd() throws Exception {
        String content = "name=value\nkevin=awesome\nkevin=again";
        assertEquals("awesome", WebScan.get(content, "kevin"));
    }
}
