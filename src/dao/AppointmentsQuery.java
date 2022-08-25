package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentTime;
import model.Appointments;
import model.Contacts;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentsQuery {
    private static JDBC DBConnection;


    /**
     * Observable list for all appointments in database.
     *
     * @return appointmentsObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllAppointments() throws SQLException {
        String sql = "SELECT * from appointments";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int appointment_ID = rs.getInt("Appointment_ID");
            String appointment_Title = rs.getString("Title");
            String appointment_Description = rs.getString("Description");
            String appointment_Location = rs.getString("Location");
            String appointment_Type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customer_ID = rs.getInt("Customer_ID");
            int user_ID = rs.getInt("User_ID");
            int contact_ID = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointment_ID, appointment_Title, appointment_Description, appointment_Location, appointment_Type, start, end, customer_ID, user_ID, contact_ID);
            appointmentsObservableList.add(appointment);
        }

        return appointmentsObservableList;
    }

    /**
     * Method that adds the appointments in the database.
     *
     * @param appointments
     */
    public static void addAppointment(Appointments appointments) {
        String sqlLocate = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
//            ps.setInt(1, appointments.getAppointmentID());
            ps.setString(1, appointments.getAppointmentTitle());
            ps.setString(2, appointments.getAppointmentDescription());
            ps.setString(3, appointments.getAppointmentLocation());
            ps.setString(4, appointments.getAppointmentType());
            ps.setTimestamp(5, Timestamp.valueOf(appointments.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointments.getEnd()));
            ps.setInt(7, appointments.getCustomerID());
            ps.setInt(8, appointments.getUserID());
            ps.setInt(9, appointments.getContactID());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Method that gets the appointmentID, Start from the database.
     * @param userName
     */
    public static ObservableList<AppointmentTime> getAppointmentsByUserName(String userName) throws SQLException {
        String sqlLocate = "SELECT Appointment_ID, Start FROM appointments " +
                "INNER JOIN users ON appointments.User_ID = users.User_ID AND users.User_Name = ? " +
                "AND Start BETWEEN NOW() AND Date_Add(Now(), Interval 15 minute)";
        ObservableList<AppointmentTime> appointmentsTimeObservableList = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
            ps.setString(1, userName);
            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointment_ID = rs.getInt("Appointment_ID");
                String start = rs.getString("Start");
                AppointmentTime appointmentTime = new AppointmentTime(appointment_ID, start);
                appointmentsTimeObservableList.add(appointmentTime);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentsTimeObservableList;

    }







    /**
     * Method that updates the appointments in the database.
     * @param appointments
     */
    public static void updateAppointment(Appointments appointments) {
        String sqlLocate = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ? , End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
            ps.setString(1, appointments.getAppointmentTitle());
            ps.setString(2, appointments.getAppointmentDescription());
            ps.setString(3, appointments.getAppointmentLocation());
            ps.setString(4, appointments.getAppointmentType());
            ps.setTimestamp(5, Timestamp.valueOf(appointments.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointments.getEnd()));
            ps.setInt(7, appointments.getCustomerID());
            ps.setInt(8, appointments.getUserID());
            ps.setInt(9, appointments.getContactID());
            ps.setInt(10, appointments.getAppointmentID());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method that deletes appointment based on Customer ID.
     * @param customers
     */
    public static void deleteAppointment(Customers customers) {
        String sqlLocate = "DELETE FROM appointments WHERE CUSTOMER_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
            ps.setInt(1, customers.getCustomerID());

            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Method that deletes appointment based on appointment ID.
     * @param appointments
     */
    public static void deleteOnlyAppointment(Appointments appointments) {
        String sqlLocate = "DELETE FROM appointments WHERE Appointment_ID = ?";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
            ps.setInt(1, appointments.getAppointmentID());

            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Method that gets all of the monthly appointments.
     * @param appointments
     */
    public static void monthlyAppointments(Appointments appointments) {
        String sqlLocate = "SELECT * FROM appointments WHERE ";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
            ps.setTimestamp(1, Timestamp.valueOf(appointments.getStart()));

            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static ObservableList<Appointments> getAppointmentsByCustomerID(int customerID) throws SQLException {
        ObservableList<Appointments> appointmentsByCustomerObservableList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();


        while (rs.next()) {
            int appointment_ID = rs.getInt("Appointment_ID");
            String appointment_Title = rs.getString("Title");
            String appointment_Description = rs.getString("Description");
            String appointment_Location = rs.getString("Location");
            String appointment_Type = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customer_ID = rs.getInt("Customer_ID");
            int user_ID = rs.getInt("User_ID");
            int contact_ID = rs.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointment_ID, appointment_Title, appointment_Description, appointment_Location, appointment_Type, start, end, customer_ID, user_ID, contact_ID);
            appointmentsByCustomerObservableList.add(appointment);
        }

        return appointmentsByCustomerObservableList;
    }


}
