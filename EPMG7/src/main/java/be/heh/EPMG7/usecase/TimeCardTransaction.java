package be.heh.EPMG7.usecase;

import be.heh.EPMG7.Context;
import be.heh.EPMG7.entity.Employee;
import be.heh.EPMG7.entity.TimeCard;
import be.heh.EPMG7.entity.HourlyClassification;
import be.heh.EPMG7.entity.PaymentClassification;
import be.heh.EPMG7.entity.Command;
import java.util.Calendar;

public class TimeCardTransaction implements Command {

    private int Empid;
    private Calendar Date;
    private double Hours;

    public TimeCardTransaction(int empid, Calendar date, double hours) {
        this.Empid = empid;
        this.Date = date;
        this.Hours = hours;
    }

    @Override
    public void execute() {
        Employee e = Context.employeeGateway.getEmployee(Empid);
        if (e != null) {
            PaymentClassification pc = e.getPayClassification();
            if (pc instanceof HourlyClassification) {
                HourlyClassification hc = (HourlyClassification) pc;
                hc.addTimeCard(new TimeCard(Date, Hours));
            } else {
                throw new RuntimeException("Tried to add timecard to non-hourly employee");
            }
        } else {
            throw new RuntimeException("No such employee");
        }
    }
}
