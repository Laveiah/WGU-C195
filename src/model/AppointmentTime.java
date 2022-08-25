package model;


public class AppointmentTime {
    private int appointmentID;
    private String start;

    public AppointmentTime(int appointmentID, String start) {
        this.appointmentID = appointmentID;
        this.start = start;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }


}
