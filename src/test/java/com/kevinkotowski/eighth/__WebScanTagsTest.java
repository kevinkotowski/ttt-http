package com.kevinkotowski.eighth;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class __WebScanTagsTest {
    @Test
    public void getAllTagsFromContent() throws Exception {
        List<String> tokens = WebScanTags.get(this.content);
        for (String token : tokens) {
            System.out.println("...token: " + token);
//            assertTrue(token.matches("\\{\\{(.*?)\\}\\}"));
        }
        assertEquals(3, tokens.size());
    }

    String content =
            "The quick brown {{player1::name}} jumps over\n" +
            "the lazy {{square::3}}. However, there was a\n" +
            "strange {{message}} to read.";
}
