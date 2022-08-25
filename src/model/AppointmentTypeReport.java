package model;

public class AppointmentTypeReport {
    private int total;
    private String type;
    private String month;

    /**
     *
     * @param total
     * @param type
     * @param month
     */
    public AppointmentTypeReport(int total, String type, String month) {
        this.total = total;
        this.type = type;
        this.month = month;
    }

    /**
     *
     * @return total
     */
    public int getTotal() {

        return total;
    }

    /**
     *
     * @return type
     */
    public String getType() {

        return type;
    }

    /**
     *
     * @return month
     */
    public String getMonth() {
        return month;
    }
}