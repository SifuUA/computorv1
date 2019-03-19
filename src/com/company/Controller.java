package com.company;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


/**
 * Created by okres on 3/9/19.
 */
public class Controller {
    public static double[] findCoef(String equation) {
        int index;
        boolean leftSide = true;
        double array[] = {0, 0, 0};
        List<ArrayList> listInList = new ArrayList<>();
        String resRepl = equation.replace("'(X(?!\\^))", "X^1").replace("--", "+").replace("+-", "-").replace("-+", "-");

        Matcher m = Pattern.compile("([-+=]?)([\\d\\.]+)?(\\*?[xX](?:\\^(\\d+))?)?")
                .matcher(resRepl);
        while (m.find()) {
            ArrayList<String> tmpList = new ArrayList<>();
            int i = 1;
            while (i < m.group().length() && i < 5) {
                tmpList.add(m.group(i));
                i++;
            }
            if (tmpList.size() > 0)
                listInList.add(tmpList);
        }

        for (ArrayList str : listInList) {
            if (str.get(0).equals("="))
                leftSide = false;
            if (str.size() == 1)
                continue;
            if (str.get(1).equals(""))
                str.add(1, 1);
            if (str.get(3).equals("") || str.get(3).equals("0"))
                index = 0;
            else if (str.get(3).equals("1"))
                index = 1;
            else
                index = 2;
            if ((str.get(0).equals("-") && !leftSide) || ((str.get(0).equals("+") || str.get(0).equals("=") || str.get(0).equals("")) && leftSide))
                array[index] = array[index] + Double.parseDouble(String.valueOf(str.get(1)));
            else
                array[index] = array[index] - Double.parseDouble(String.valueOf(str.get(1)));
        }
        return array;
    }


    public static String[] getAnswer(double[] coefic, double discr) {
        String[] arr = new String[2];
        if (coefic[2] != 0.0) {
            if (discr > 0.0) {
                arr[0] = String.valueOf((-coefic[1] - Math.pow(discr, (1 / 2d))) / (2 * coefic[2]));
                arr[1] = String.valueOf((-coefic[1] + Math.pow(discr, (1 / 2d))) / (2 * coefic[2]));
                return arr;
            } else if (discr < 0.0) {
                double n1 = -coefic[1] / (2 * coefic[2]);
                double n2 = Math.pow(-discr, (1 / 2d)) / (2 * coefic[2]);
                arr[0] = n1 + "-" + new DecimalFormat("#.######",
                        DecimalFormatSymbols.getInstance(Locale.US)).format(n2) + "j";
                arr[1] = n1 + "+" + new DecimalFormat("#.######",
                        DecimalFormatSymbols.getInstance(Locale.US)).format(n2) + "j";
                return arr;

            } else {
                arr[0] = String.valueOf(-coefic[1] / (2 * coefic[2]));
                arr[1] = "+";
            }
        } else if (coefic[1] != 0.0) {
            if (-coefic[0] / coefic[1] == -0.0 || -coefic[0] / coefic[1] == -0) {
                arr[0] = String.valueOf(0);
                arr[1] = "+";
                return arr;
            }
            arr[0] = String.valueOf(-coefic[0] / coefic[1]);
            arr[1] = "+";
            return arr;
        } else {
            arr[0] = "+";
            arr[1] = "+";
        }
        return arr;
    }
}
//5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0
