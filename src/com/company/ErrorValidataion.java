package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by okres on 3/9/19.
 */
public class ErrorValidataion {

    private static final String regex = "(\\^- ?[0-9]|\\^ ?[3-9])";
    private static final Pattern pattern = Pattern.compile(regex);

    public static String checkErrors() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Please enter your equation:\n");
            String result = reader.readLine();

            if (result.isEmpty()) {
                throw new IllegalArgumentException();
            } else if (!doMatch(pattern, result))
            {
                return result = result.replaceAll("\\s+", "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean doMatch(Pattern pattern, String str) {
        return pattern.matcher(str).matches();
    }
}
