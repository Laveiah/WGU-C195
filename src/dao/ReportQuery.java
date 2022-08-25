package dao;

import controller.AppointmentMonthReport;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AppointmentTypeReport;
import model.Countries;
import model.CountryReport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportQuery {

    /**
     * Observable list for the total number of appointment months and type in the database.
     *
     * @return appointmentTypeObservableList
     * @throws SQLException
     */

    public static ObservableList<AppointmentTypeReport> getAppointmentTypeReport() throws SQLException {
        ObservableList<AppointmentTypeReport> appointmentTypeObservableList = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(Appointment_ID), Type, MONTHNAME(Start) AS Month FROM appointments GROUP BY Type, Month";
//      String sql = "SELECT COUNT(Appointment_ID), Type FROM appointments GROUP BY Type";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            int total = rs.getInt("COUNT(Appointment_ID)");
            String type = rs.getString("Type");
            String month = rs.getString("month");
            AppointmentTypeReport report = new AppointmentTypeReport(total, type, month);
            appointmentTypeObservableList.add(report);

        }

        return appointmentTypeObservableList;
    }


//    public static ObservableList<AppointmentMonthReport> getAppointmentMonthReport() throws SQLException {
//        ObservableList<AppointmentMonthReport> appointmentMonthObservableList = FXCollections.observableArrayList();
//        String sql = "SELECT COUNT(Appointment_ID), MONTHNAME(Start) AS Month FROM appointments GROUP BY monthname(Start)";
//
//        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//
//            int total = rs.getInt("COUNT(Appointment_ID)");
//            String month = rs.getString("Month");
//            AppointmentMonthReport report = new AppointmentMonthReport(total, month);
//            appointmentMonthObservableList.add(report);
//
//        }
//
//        return appointmentMonthObservableList;
//    }

    /**
     * Observable list for the country and the number of countries in the database.
     *
     * @return countryCountReportObservableList
     * @throws SQLException
     */
    public static ObservableList<CountryReport> getCountryCount() throws SQLException {
        ObservableList<CountryReport> countryCountReportObservableList = FXCollections.observableArrayList();
        String sql = "SELECT COUNT(Country_ID), Country FROM countries group by Country";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            int total = rs.getInt("COUNT(Country_ID)");
            String country = rs.getString("Country");
            CountryReport report = new CountryReport(country, total);
            countryCountReportObservableList.add(report);

        }

        return countryCountReportObservableList;
    }

}

