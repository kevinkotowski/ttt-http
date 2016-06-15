package com.kevinkotowski.eighth;

import java.util.Scanner;

/**
 * Created by kevinkotowski on 6/14/16.
 */
public class WebScan {
    public static String get(String content, String name) {
        String value = null;
        Scanner scanner = new Scanner(content);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] nameValue;
            nameValue = line.split("=");
            if (nameValue.length == 2) {
                if (nameValue[0].equals(name)) {
                    value = nameValue[1];
                    break;
                }
            }
        }
        scanner.close();
        return value;
    }
}
