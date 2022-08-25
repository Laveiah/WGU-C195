package dao;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesQuery extends Countries {

    public CountriesQuery(int countriesID, String countriesName) {
        super(countriesID, countriesName);
    }

    /**
     * Method that gets the country id from the database.
     * @param countryID
     * @return country
     */
    public static Countries getCountry(int countryID) {
        String sqlLocate = "SELECT COUNTRY_ID, COUNTRY FROM COUNTRIES WHERE COUNTRY_ID = ?";//pass in
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
            ps.setInt(1, countryID); //set parameter where ? is at
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String countryName = rs.getString("country");
                Countries country = new Countries(countryID, countryName);

                return country;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Method that gets the country id from division id in the database.
     * @param divisionID
     * @return null
     */
    public static Countries getCountryByDivision(int divisionID) {
        String sqlLocate = "SELECT C.COUNTRY_ID, C.COUNTRY FROM COUNTRIES AS C INNER JOIN FIRST_LEVEL_DIVISIONS AS D ON C.COUNTRY_ID = D.COUNTRY_ID AND D.DIVISION_ID = ?";//pass in
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlLocate);
            ps.setInt(1, divisionID); //set parameter where ? is at
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                int countryID = rs.getInt("country_ID");
                String countryName = rs.getString("country");
                Countries country = new Countries(countryID, countryName);

                return country;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    /**
     * Observable list for all countries in database.
     * @throws SQLException
     * @return countriesObservableList
     */
    public static ObservableList<Countries> getCountries() throws SQLException {
        String sql = "SELECT Country_ID, Country FROM countries";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Countries> countriesObservableList = FXCollections.observableArrayList();


        while (rs.next()) {
            int country_ID = rs.getInt("Country_ID");
            String country_Name = rs.getString("Country");
            Countries country = new Countries(country_ID, country_Name);
            countriesObservableList.add(country);

        }
        return countriesObservableList;
    }
}
