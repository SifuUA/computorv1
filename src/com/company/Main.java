package com.company;

import static com.company.Controller.findCoef;
import static com.company.Controller.getAnswer;
import static com.company.ErrorValidataion.checkErrors;
import static com.company.OutPut.*;

/**
 * Created by okres on 3/9/19.
 */

public class Main {

    public static void main(String[] args) {
        String correctEquation = checkErrors();
        double [] coeficient = findCoef(correctEquation);
        printReducedForm(coeficient);
        int degree = printAndGetDegree(coeficient);
        double discriminant = Math.pow(coeficient[1], 2) - 4 * coeficient[0]*coeficient[2];
        printDescr(discriminant, degree);
        printResult(getAnswer(coeficient, discriminant), coeficient);
    }
}
