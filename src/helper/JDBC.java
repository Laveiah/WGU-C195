package helper;

import dao.AppointmentsQuery;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    private static Connection connection = null;  // Connection Interface
    private static PreparedStatement preparedStatement;

    public static void makeConnection() {

        try {
            Class.forName(driver); // Locate Driver
            //password = Details.getPassword(); // Assign password
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            System.out.println("Connection successful!");
        }
        catch(ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        }
        catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void makePreparedStatement(String sqlStatement, Connection conn) throws SQLException {
        if (conn != null)
            preparedStatement = conn.prepareStatement(sqlStatement);
        else
            System.out.println("Prepared Statement Creation Failed!");
    }
    public static PreparedStatement getPreparedStatement() throws SQLException {
        if (preparedStatement != null)
            return preparedStatement;
        else System.out.println("Null reference to Prepared Statement");
        return null;
    }


    public static boolean appointmentOverlap(int customer_ID, LocalDateTime start, LocalDateTime end, int appointment_ID) throws SQLException {
        ObservableList<Appointments> appointments = AppointmentsQuery.getAppointmentsByCustomerID(customer_ID);
            for (Appointments a: appointments){
                if(a.getAppointmentID() == appointment_ID) {
                    continue; //brings it back to line 69
                }
                LocalDateTime aStart = a.getStart();
                LocalDateTime eEnd = a.getEnd();


                //check start time for overlap
                //(aStart >= start) && (aStart < end)
                //return true
//                if((aStart.isAfter(start) || (aStart.isEqual(start))) && (aStart.isBefore(end))){
//                    return true;
//                }
                if(aStart.isEqual(start) || (eEnd.isEqual(end))){
                    return true;
                }

                //check end time for overlap
                //aEnd > start && aEnd <= end
                //return true
//                if ((eEnd.isAfter(start)) && (eEnd.isBefore(end) || eEnd.isEqual(end))){
//                    return true;
//                }
                if(start.isBefore(aStart) && (end.isAfter(aStart))){
                    return true;
                }

                //check whole appointment swallows proposed times
                //aStart <= Start && aEnd >= end
                //true

//                if ((aStart.isBefore(start)) || (aStart.isEqual(start)) && (eEnd.isAfter(end)) || (eEnd.isEqual(end))){
//                    return true;
//                }
                if(start.isAfter(aStart) && (start.isBefore(eEnd))){
                    return true;
                }

                if(start.isBefore(aStart) && (end.isAfter(eEnd))){
                    return true;
                }

                if(start.isAfter(aStart) && (end.isBefore(eEnd))){
                    return true;
                }
            }
        return false;
    }

}
