package com.epam.engx.cleancode.functions.task5;

import java.util.Date;

public final class DateProviderTestHelper {

    private DateProviderTestHelper() {
        throw new RuntimeException("Can not be instantiated");
    }

    public static Date getDirectlyIncrementedDate(DateProvider dateUtil, Date date) {
        return dateUtil.changeToIncrementedDate(date);
    }

    public static Date getInverseIncrementedDate(DateProvider dateUtil, Date date) {
        return dateUtil.changeToInverseDate(date);
    }
}
