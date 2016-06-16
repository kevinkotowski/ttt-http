package com.kevinkotowski.eighth;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Created by kevinkotowski on 6/15/16.
 */
public class WebReplaceTags {
    public static List<String> get(String content) {
        List<String> tokens = new ArrayList<String>();
        if (content != null) {
            Scanner scanner = new Scanner(content);
            while (scanner.hasNextLine()) {
                scanner.findInLine("\\{\\{(.*?)\\}\\}");
                try {
                    MatchResult match = scanner.match();
                    String token = match.group(1);
                    tokens.add(token);
                } catch (IllegalStateException e) { }
                scanner.nextLine();
            }
            scanner.close();
        }
        return tokens;
    }
}
