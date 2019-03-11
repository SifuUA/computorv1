package com.company;


import java.text.DecimalFormat;

/**
 * Created by okres on 3/9/19.
 */
public class OutPut {
    static void printReducedForm(double[] array) {
        String result;

        if (array[2] != 0)
            result = String.format("Reduced form: %s * X^0 + %s * X^1 + %s * X^2 = 0",
                    new DecimalFormat().format(array[0]),
                    new DecimalFormat().format(array[1]),
                    new DecimalFormat().format(array[2]));
        else if (array[1] != 0)
            result = String.format("Reduced form: %s * X^0 + %s * X^1 = 0",
                    new DecimalFormat().format(array[0]),
                    new DecimalFormat().format(array[1]));
        else
            result = String.format("Reduced form: %s * X^0 = 0",
                    new DecimalFormat().format(array[0]));
        System.out.println(result);
    }

    static int printAndGetDegree(double[] array) {
        int degree;
        if (array[2] != 0)
            degree = 2;
        else if (array[1] != 0)
            degree = 1;
        else
            degree = 0;
        System.out.println("Polynomial degree: " + degree);
        return degree;
    }

    static void printDescr(double discr, int degree) {
        if (degree > 0) {
            if (discr > 0)
                System.out.println("Discriminant is strictly positive");
            else if (discr < 0)
                System.out.println("Discriminant is strictly negative");
            else
                System.out.println("Discriminant is equal to 0");
        }
    }

    static void printResult(String[] arr, double[] coefic) {
        if (arr[0].equals("+") && arr[1].equals("+") && coefic[0] != 0.0)
            System.out.println("There is no solution :(");
        else if (!arr[0].equals("+") && !arr[1].equals("+"))
            System.out.println(String.format("The solutions are \n%g\n%g", Double.parseDouble(arr[0]), Double.parseDouble(arr[1])));
        else if (arr[0].equals("+") && arr[1].equals("+") && coefic[0] == 0.0)
            System.out.println("All real number are solution");
        else
            System.out.println(String.format("The solution is %g", Double.parseDouble(arr[0])));
    }
}
//5 * X^0 + 4 * X^1 - 9.3 * X^2 = 1 * X^0