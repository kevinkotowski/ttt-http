package com.kevinkotowski.eighth;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class __WebReplaceTagsTest {
    @Test
    public void getAllTagsFromContent() throws Exception {
        List<String> tokens = WebReplaceTags.get(this.content);
        for (String token : tokens) {
            System.out.println("...token: " + token);
        }
        assertEquals(3, tokens.size());
        assertEquals("player1::name", tokens.get(0));
        assertEquals("square::3", tokens.get(1));
        assertEquals("message", tokens.get(2));
    }

    String content =
            "The quick brown {{player1::name}} jumps over\n" +
            "the lazy {{square::3}}. However, there was a\n" +
            "strange {{message}} to read.";
}
