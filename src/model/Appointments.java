package model;

import java.time.LocalDateTime;

public class Appointments {
    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime start;
    private LocalDateTime end;
    public int customerID;
    public int userID;
    public int contactID;


//    /**
//     *
//     * @param appointmentID
//     * @param appointmentTitle
//     * @param appointmentDescription
//     * @param appointmentLocation
//     * @param appointmentType
//     * @param contactID
//     * @param customerID
//     * @param endDate
//     * @param endTime
//     * @param startDate
//     * @param startTime
//     * @param userID
//     *
//     * */
//    public Appointments(int appointmentID, String appointmentTitle, String appointmentDescription,
//                        String appointmentLocation, String appointmentType, String startDate, String startTime, String endDate, String endTime, int customerID,
//                        int userID, int contactID) {
//        this.appointmentID = appointmentID;
//        this.appointmentTitle = appointmentTitle;
//        this.appointmentDescription = appointmentDescription;
//        this.appointmentLocation = appointmentLocation;
//        this.appointmentType = appointmentType;
//        this.start = startDate + " " + startTime;
//        this.end = endDate + " " + endTime;
//        this.customerID = customerID;
//        this.userID = userID;
//        this.contactID = contactID;
//    }

    public Appointments(int appointmentID, String appointmentTitle, String appointmentDescription,
                        String appointmentLocation, String appointmentType, LocalDateTime start, LocalDateTime end,
                        int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public Appointments() {

    }

    /**
     *
     * @return appointmentID
     */
    public int getAppointmentID() {

        return appointmentID;
    }

    /**
     *
     * @return appointmentTitle
     */
    public String getAppointmentTitle() {

        return appointmentTitle;
    }

    /**
     *
     * @return appointmentDescription
     */
    public String getAppointmentDescription() {

        return appointmentDescription;
    }

    /**
     *
     * @return appointmentLocation
     */
    public String getAppointmentLocation() {

        return appointmentLocation;
    }

    /**
     *
     * @return appointmentType
     */
    public String getAppointmentType() {

        return appointmentType;
    }

    /**
     *
     * @return start
     */
    public LocalDateTime getStart() {

        return start;
    }

    /**
     *
     * @return end
     */
    public LocalDateTime getEnd() {

        return end;
    }

    /**
     *
     * @return customerID
     */
    public int getCustomerID () {

        return customerID;
    }

    /**
     *
     * @return userID
     */
    public int getUserID() {

        return userID;
    }

    /**
     *
     * @return contactID
     */
    public int getContactID() {

        return contactID;
    }


}