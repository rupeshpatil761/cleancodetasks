package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    private static final int MONTHS_IN_YEAR = 12;

    public static double calculateMonthlyPayment(
            int principalAmount, int termInYears, double rateOfInterest) {

        validatePaymentInputValues(principalAmount,termInYears,rateOfInterest);

        double rateOfInterestInDecimal = convertRateOfInterestIntoDecimal(rateOfInterest);
        double termInMonths = convertMortgageTermInMonths(termInYears);

        return calculateFinalMonthlyPayment(rateOfInterestInDecimal, principalAmount, termInMonths);
    }

    private static double calculateFinalMonthlyPayment(double rateOfInterestInDecimal
            , double principalAmount, double termInMonths){
        if(rateOfInterestInDecimal==0){
            return computeMonthlyPaymentWithoutInterest(principalAmount, termInMonths);
        }
        double monthlyInterestRate = rateOfInterestInDecimal / MONTHS_IN_YEAR;
        return computeMonthlyPaymentWithInterest(principalAmount, monthlyInterestRate, termInMonths);
    }

    private static double convertRateOfInterestIntoDecimal(double rateOfInterest) {
        return rateOfInterest / 100.0;
    }

    private static double convertMortgageTermInMonths(double termOfMortgage) {
        termOfMortgage = termOfMortgage * MONTHS_IN_YEAR;
        return termOfMortgage;
    }

    private static double computeMonthlyPaymentWithoutInterest(double principalAmount, double termInMonths){
        return principalAmount/termInMonths;
    }

    private static double computeMonthlyPaymentWithInterest(double principalAmount, double monthlyInterestRate, double termInMonths){
        return (principalAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -termInMonths));
    }

    private static void validatePaymentInputValues(int principalAmount, int termOfMortgage, double rateOfInterest)
            throws InvalidInputException {
        if(isAnyPaymentParemeterNotValid(principalAmount, termOfMortgage, rateOfInterest)){
            throw new InvalidInputException("Negative values are not allowed");
        }
    }
    private static boolean isAnyPaymentParemeterNotValid(int principalAmount, int termOfMortgage, double rateOfInterest){
        return termOfMortgage <= 0 || rateOfInterest < 0 || principalAmount < 0;
    }
}
