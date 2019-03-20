package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            int res = doMatch(pattern, result);

            if (correctInput(result, res)) {
                return result.replaceAll("\\s+", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean correctInput(String result, int res) {
        if (result.isEmpty()) {
            System.out.println("Input is empty ...");
            throw new IllegalArgumentException();
        } else if (!checkInput(result)) {
            System.out.println("Input equation is not valid!");
        } else if (res > 2) {
            System.out.println("Polynomial degree: " + res);
            System.out.println("The polynomial degree is strictly greater than " + res + ", I can't solve.");
            System.exit(1);
        } else {
            return true;
        }
        return false;
    }

    private static boolean checkInput(String result) {
        String target = "1234567890^+=-*/X. ";
        char[] charsArray = result.toCharArray();
        for (int i = 0; i < charsArray.length; i++) {
            if (!target.contains(String.valueOf(charsArray[i])))
                return false;
        }
        return true;
    }

    private static int doMatch(Pattern pattern, String str) {
        Matcher m = pattern.matcher(str);
        String res = "0";
        while (m.find()) {
            res = m.group();
        }
        res = res.replaceAll("\\D+", "");
        return Integer.parseInt(res);
    }
}
