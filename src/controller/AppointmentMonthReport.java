package controller;

public class AppointmentMonthReport {

    private int total;
    private String month;


    public AppointmentMonthReport(int total, String month){
        this.total = total;
        this.month = month;
    }

    public int getTotal() {
        return total;
    }

    public String getMonth(){
        return month;
    }
}
