package com.kevinkotowski.eighth;

import java.util.Scanner;

/**
 * Created by kevinkotowski on 6/16/16.
 */
public class WebParseOperation {
    public static String[] parse(String content) {
        String[] tokens = new String[2];
        if (content != null) {
            Scanner scanner = new Scanner(content);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tokens = line.split("=");
            }
            scanner.close();
        }
        return tokens;
    }

}
