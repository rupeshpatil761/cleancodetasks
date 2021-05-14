package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator implements Profitable {

    private static final int SENIOR_ELIGIBLE_AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_INTEREST_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;
    private static final int LEAP_YEAR_SHIFT = 1;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return calculateInterestBasedOnCustomerAge(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(),
                accountDetails.getStartDate()) > BONUS_AGE;
    }

    private BigDecimal calculateInterestBasedOnCustomerAge(AccountDetails accountDetails) {
        double rateOfInterest = INTEREST_PERCENT;
        int currentAge = durationBetweenDatesInYears(accountDetails.getStartDate(),new Date());
        if (SENIOR_ELIGIBLE_AGE <= currentAge) {
            rateOfInterest = SENIOR_INTEREST_PERCENT;
        }
        return BigDecimal.valueOf(getInterestRateBasedOnCitizenType(accountDetails, rateOfInterest));
    }

    private double getInterestRateBasedOnCitizenType(
            AccountDetails accountDetails, double rateOfInterest){
       double accountBalance = accountDetails.getBalance().doubleValue();
       int durationInYears = durationBetweenDatesInYears(accountDetails.getStartDate(),new Date());
       double principalAmount = accountBalance * durationInYears;
       return principalAmount * rateOfInterest / 100;
    }

    private boolean isCurrentDateLessThanStartDate(Calendar startCalendar, Calendar endCalendar){
        return endCalendar.get(Calendar.DAY_OF_YEAR) +
                LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR);
    }

    private int durationBetweenDatesInYears(Date startDate, Date toDate) {
        Calendar startCalendar = getCalendarFromDate(startDate);
        Calendar endCalendar = getCalendarFromDate(toDate);
        return isCurrentDateLessThanStartDate(startCalendar,endCalendar) ? -1 :
                endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
    }

    private Calendar getCalendarFromDate(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
