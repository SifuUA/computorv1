package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

            if (result.isEmpty()) {
                throw new IllegalArgumentException();
            } else if (res > 2) {
                System.out.println("Polynomial degree: " + res);
                System.out.println("The polynomial degree is stricly greater than " + res + ", I can't solve.");
                System.exit(1);
            } else
                return result = result.replaceAll("\\s+", "");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int doMatch(Pattern pattern, String str) {
        Matcher m = pattern.matcher(str);
        String res = "0";
        int tmp = 0;
        while (m.find()) {
            res = m.group();
        }
        res = res.replaceAll("\\D+", "");
        /*if (res.equals("0")){
            res = str.replaceAll("\\D+", " ");
            String[] list = res.split(" ");
            for (String num : list) {
                if (Integer.parseInt(num) > tmp)
                   tmp = Integer.parseInt(num);
            }
            return tmp;
        }*/
        return Integer.parseInt(res);
    }
}
