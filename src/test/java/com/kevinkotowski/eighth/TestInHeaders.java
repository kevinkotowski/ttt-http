package com.kevinkotowski.eighth;

import java.util.List;

/**
 * Created by kevinkotowski on 6/16/16.
 */
public class TestInHeaders {
    public static boolean check(List<String> headers, String match) {
        boolean foundIt = false;
        for (String header: headers) {
            if (header.contains(match)) {
                foundIt = true;
                break;
            }
        }
        return foundIt;
    }
}
