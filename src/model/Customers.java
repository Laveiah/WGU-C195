package model;

import dao.CountriesQuery;

public class Customers {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private int divisionID;

    /**
     *
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerPhoneNumber
     * @param customerPostalCode
     * @param divisionID
     */
    public Customers(int customerID, String customerName, String customerAddress, String customerPostalCode,
                     String customerPhoneNumber, int divisionID) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.divisionID = divisionID;
    }


    /**
     *
     * @return customerID
     */
    public int getCustomerID() {

        return customerID;
    }

    /**
     *
     * @param customerID
     */
    public void setCustomerID(int customerID) {

        this.customerID = customerID;
    }

    /**
     *
     * @return customerName
     */
    public String getCustomerName() {

        return customerName;
    }

    /**
     *
     * @param customerName
     */
    public void setCustomerName(String customerName) {

        this.customerName = customerName;
    }

    /**
     *
     * @return customerAddress
     */
    public String getCustomerAddress() {

        return customerAddress;
    }

    /**
     *
     * @param customerAddress
     */
    public void setCustomerAddress(String customerAddress) {

        this.customerAddress = customerAddress;
    }

    /**
     *
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {

        return customerPostalCode;
    }

    /**
     *
     * @param customerPostalCode
     */
    public void setCustomerPostalCode(String customerPostalCode) {

        this.customerPostalCode = customerPostalCode;
    }

    /**
     *
     * @return customerPhoneNumber
     */
    public String getCustomerPhoneNumber() {

        return customerPhoneNumber;
    }

    /**
     *
     * @param customerPhoneNumber
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {

        this.customerPhoneNumber = customerPhoneNumber;
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
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {

        this.divisionID = divisionID;
    }

    /**
     *
     * @param divisionID
     */
    public void setCustomerDivisionID(Integer divisionID) {

        this.divisionID = divisionID;
    }

    /**
     *
     * @return divisionID
     */
    public Countries getCountry() {

        return CountriesQuery.getCountryByDivision(divisionID);
    }

}