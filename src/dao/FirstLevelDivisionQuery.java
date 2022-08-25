package dao;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionQuery {

    private static JDBC DBConnection;

    /**
     * Observable list for all first level division in database.
     * @throws SQLException
     * @return firstLevelDivisionsObservableList
     */
    public static ObservableList<FirstLevelDivision> getAllFirstLevelDivisions() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<FirstLevelDivision> firstLevelDivisionsObservableList = FXCollections.observableArrayList();


        while (rs.next()) {
            int division_ID = rs.getInt("Division_ID");
            String division_Name = rs.getString("Division");
            int country_ID = rs.getInt("COUNTRY_ID");
            FirstLevelDivision newFLD = new FirstLevelDivision(division_ID, division_Name, country_ID);
            firstLevelDivisionsObservableList.add(newFLD);
        }
        return firstLevelDivisionsObservableList;

    }

    /**
     * Method that gets the division ID from the database.
     * @param division_ID
     * @return newFLD
     */
    public static FirstLevelDivision getAllFirstLevelDivisionsByID(int division_ID) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE DIVISION_ID = " + division_ID;
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        FirstLevelDivision newFLD = null;


        if (rs.next()) {
            String division_Name = rs.getString("Division");
            int country_ID = rs.getInt("COUNTRY_ID");
            newFLD = new FirstLevelDivision(division_ID, division_Name, country_ID);
        }
        return newFLD;
    }

    /**
     * Observable list for first level division by country in database.
     * @param country_ID
     * @throws SQLException
     * @return divisionObservableList
     */
    // division based on the country. get all first level division by the country
    public static ObservableList<FirstLevelDivision> divisionByCountry(int country_ID) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = " + country_ID;
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<FirstLevelDivision> divisionObservableList = FXCollections.observableArrayList();


        while (rs.next()) {
            int division_ID = rs.getInt("Division_ID");
            String division_Name = rs.getString("Division");
            // int country_ID = rs.getInt("COUNTRY_ID");
            FirstLevelDivision newFLD = new FirstLevelDivision(division_ID, division_Name, country_ID);
            divisionObservableList.add(newFLD);
        }
        return divisionObservableList;
    }
}

