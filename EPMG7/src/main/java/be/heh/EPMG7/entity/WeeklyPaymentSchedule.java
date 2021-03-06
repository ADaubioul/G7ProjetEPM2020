package be.heh.EPMG7.entity;

import java.util.Calendar;

public class WeeklyPaymentSchedule implements PaymentSchedule {

    @Override
    public boolean isPayday(Calendar date) {
        return date.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        Calendar monday = (Calendar) payDate.clone();
        monday.roll(Calendar.DAY_OF_MONTH, -6);
        return monday;
    }
}
