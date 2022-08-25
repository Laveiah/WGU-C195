package model;

import dao.CountriesQuery;
import dao.FirstLevelDivisionQuery;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class FirstLevelDivision {
    private int divisionID;
    private String divisionName;
    public int country_ID;

    /**
     *
     * @param divisionID
     * @param divisionName
     * @param country_ID
     */
    public FirstLevelDivision(int divisionID, String divisionName, int country_ID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.country_ID = country_ID;
    }

    public FirstLevelDivision(String division_name) {
    }

    @Override
    public String toString() {
        return divisionName;
    }

    /**
     *
     * @return divisionID
     */
    public int getDivisionID() {

        return divisionID;
    }

    /**
     *
     * @return divisionName
     */
    public String getDivisionName() {

        return divisionName;
    }

 
    public int getCountry_ID() {

        return country_ID;
    }
}